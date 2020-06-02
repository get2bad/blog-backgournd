package com.wills.blog.controller;

import com.alibaba.fastjson.JSON;
import com.wills.blog.bean.*;
import com.wills.blog.service.*;
import com.wills.blog.util.ActiveID;
import com.wills.blog.util.CaptchaUtil;
import com.wills.blog.util.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.*;

@RestController
@RequestMapping(value = "user")
@Api(tags = "用户登陆/注册")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private JavaMailSender sender;
    @Value("${wills.redis.user.login}")
    private String USER_LOGIN_KEY;
    @Value("${wills.redis.user.role}")
    private String USER_ROLE_KEY;
    @Value("${wills.redis.role.permission}")
    private String ROLE_PERMISSION_KEY;
    @Value("${wills.email.from}")
    private String mailFrom;

    @GetMapping("/not_login")
    @ApiOperation(value = "没有登录")
    public Result notLogin(){
        return new Result(false,405,"您没有登录哦，请您重新尝试登录");
    }

    @GetMapping("/not_permit")
    @ApiOperation(value = "没有权限")
    public Result notPermit(){
        return new Result(false,405,"您没有相应的权限哦，请您重新尝试！");
    }

    /**
     * 重置用户密码接口，权限: 任何人，包括匿名用户
     * @param userInfo
     * @return
     * @throws MessagingException
     */
    @PostMapping("/resetPwd")
    @ApiOperation(value = "用户重置密码")
    @RequiresGuest
    public Result resetPwd(@RequestBody UserInfo userInfo) throws MessagingException {
        System.out.println(userInfo);
        int id = userInfo.getUserId();
        String email = userInfo.getEmail();
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setFrom(mailFrom);
        helper.setTo(email);
        helper.setSubject("Wills博客：重置密码");
        StringBuffer sb = new StringBuffer();
        String  newPwd = ActiveID.getRandomUUID().substring(0,6);
        sb.append("<h1>重置密码：</h1>").append("<p>欢迎您使用重置密码服务，您的新密码是：<span style='color:#F00'>"+ newPwd +"</span><br />请您妥善保管新的新密码，感谢您使用Wills博客</p>");
        helper.setText(sb.toString(),true);
        sender.send(message);
        userService.resetPwd(id,MD5Util.encrypt(newPwd));
        return Result.buildSuccess();
    }

    /**
     * 获取所有用户的接口
     * @param pageHelper
     * @return
     */
    @PostMapping("/getAll")
    @ApiOperation(value = "获取用户列表")
    @RequiresRoles(value = {"系统管理员"})
    public Result<Map<String,Object>> getAllUser(@RequestBody WillsPageHelper pageHelper){
        Map<String,Object> info = new HashMap<String,Object>();
        List<User> list = userService.getAllUser(pageHelper);
        List<UserTotal> totalList = new ArrayList<>();
        if(list != null) {
            for (User user : list) {
                UserInfo u = userInfoService.getByUserId(user.getUserId());
                totalList.add(new UserTotal(user,u));
            }
        }
        info.put("allUser",totalList);
        info.put("total",userService.getTotalCount());
        return new Result<Map<String,Object>>(true, StatusCode.STATUS_OK,"获取全部用户信息成功！", info);
    }

    /**
     * 会员注册接口
     * @param userTotal
     * @return
     */
    @PutMapping("/regist")
    @ApiOperation(value = "注册用户")
    @RequiresGuest
    public Result regist(@RequestBody UserTotal userTotal){
        User user = userTotal.getUser();
        user.setPassword(MD5Util.encrypt(user.getPassword()));
        userService.regist(user);
        User one = userService.findOne(user);
        UserInfo userInfo = userTotal.getUserInfo();
        userInfo.setUserId(one.getUserId());
        userInfoService.regist(userInfo);
        return new Result();
    }

    /**
     * <b>已经废弃！</b></></>使用shiro后，所有的接口以token交互的方式进行，所以不需要本接口来确定是否是登录状态
     * @param token
     * @return
     */
    @Deprecated
    @GetMapping("/login/{token}")
    @ApiOperation(value = "验证用户是否还在已登陆状态 200 有 600 没有")
    @RequiresGuest
    public Result login(@PathVariable String token){
        boolean exists = redisService.ifKeyExists(USER_LOGIN_KEY + ":" +token);
        if(exists) {
            return Result.buildSuccess();
        }else{
            return new Result(false,StatusCode.STATUS_REQUIRE_FALL,"用户未登录");
        }
    }

    /**
     * 用户登录
     * @param user 获取前台传送的User对象进行封装
     * @param token 指的是万象验证成功后传回的token
     * @return
     * @throws Exception
     */
    @PostMapping("/login/{token}")
    @ApiOperation(value = "用户登陆操作")
    public Result login(@RequestBody User user,@PathVariable String token) throws Exception {
        // 先验证token是否正确验证，如果正确进行下一步
        if(CaptchaUtil.verifyToken(token)){
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserName(),user.getPassword());
            try {
                subject.login(usernamePasswordToken);
                // 获取token
                String sessionId = subject.getSession().getId().toString();
                log.info(user.getUserName() + "登录成功！");
                return new Result(true,StatusCode.STATUS_OK,"登陆成功！",sessionId);
            } catch (AuthenticationException e) {
                log.info(user.getUserName() + "登录失败，可能是密码错误！");
                return new Result(false,StatusCode.STATUS_REQUIRE_FALL,"用户名/密码错误，请重新尝试！");
            }
        }else{
            return new Result(false,StatusCode.STATUS_REQUIRE_FALL,"需要重新验证验证码！");
        }

    }

    @PostMapping("/update")
    @ApiOperation(value = "修改用户资料操作")
    @RequiresAuthentication // 需要登录的用户权限
    public Result update(@RequestBody UserInfo userInfo){
        System.out.println(userInfo);
        userService.updateUserData(userInfo);
        return new Result();
    }

    /**
     * 退出登录 头部一定要带token
     * @return
     */
    @GetMapping("/logout")
    @ApiOperation(value = "退出登录")
    public Result logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Result.buildSuccess();
    }

    /**
     * 禁止某一用户的登录状态
     * @param id
     * @return
     */
    @DeleteMapping("/ban/{id}")
    @ApiOperation(value = "禁止某一用户登陆")
    @RequiresRoles(value = {"系统管理员"})
    public Result banUser(@PathVariable("id")int id){
        userService.banUser(id);
        return new Result();
    }

    /**
     * 获得某个用户的详细信息 需要的权限，系统管理员
     * @param id
     * @return
     */
    @GetMapping("/getById/{id}")
    @ApiOperation(value = "获得某个用户的详细信息")
    @RequiresRoles(value = {"系统管理员"})
    public Result getById(@PathVariable("id") int id) {
        User user = userService.getById(id);
        return Result.buildSuccess(user);
    }
}
