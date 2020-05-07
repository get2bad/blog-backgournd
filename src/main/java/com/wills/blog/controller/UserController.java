package com.wills.blog.controller;

import com.alibaba.fastjson.JSON;
import com.wills.blog.bean.*;
import com.wills.blog.service.*;
import com.wills.blog.util.ActiveID;
import com.wills.blog.util.CaptchaUtil;
import com.wills.blog.util.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @PostMapping("/resetPwd")
    @ApiOperation(value = "用户重置密码")
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

    @PostMapping("/getAll")
    @ApiOperation(value = "获取用户列表")
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

    @PutMapping("/regist")
    @ApiOperation(value = "注册用户")
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

    @GetMapping("/login/{token}")
    @ApiOperation(value = "验证用户是否还在已登陆状态 200 有 600 没有")
    public Result login(@PathVariable String token){
        boolean exists = redisService.ifKeyExists(USER_LOGIN_KEY + ":" +token);
        if(exists) {
            return Result.buildSuccess();
        }else{
            return new Result(false,StatusCode.STATUS_REQUIRE_FALL,"用户未登录");
        }
    }

    @PostMapping("/login/{token}")
    @ApiOperation(value = "用户登陆操作")
    public Result login(@RequestBody User user,@PathVariable String token) throws Exception {
        // 先验证token是否正确验证，如果正确进行下一步
        if(CaptchaUtil.verifyToken(token)){
            User one = userService.findOne(user);
            if(one != null){
                if(one.getPassword().equals(MD5Util.encrypt(user.getPassword()))){
                    List<Role> userRole = roleService.getUserRole(one.getUserId());
                    for (Role role : userRole) {
                        List<Permission>  all=permissionService.getRolePermission(role.getRoleId());
                        role.setAllPermission(all);
                    }
                    // TODO token问题没有搞定，等前端真正做完开始对接时进行处理token问题
                    redisService.login(token, JSON.toJSONString(userRole));
                    return new Result(true,StatusCode.STATUS_OK,"登陆成功！");
                }else {
                    return new Result(false,StatusCode.STATUS_REQUIRE_FALL,"用户名/密码错误，请重新尝试！");
                }
            }else{
                return Result.buildServerError();
            }
        }else{
            return new Result(false,StatusCode.STATUS_REQUIRE_FALL,"需要重新验证验证码！");
        }

    }

    @PostMapping("/update")
    @ApiOperation(value = "修改用户资料操作")
    public Result update(@RequestBody UserInfo userInfo){
        System.out.println(userInfo);
        userService.updateUserData(userInfo);
        return new Result();
    }

    @DeleteMapping("/ban/{id}")
    @ApiOperation(value = "禁止某一用户登陆")
    public Result banUser(@PathVariable("id")int id){
        userService.banUser(id);
        return new Result();
    }

    @GetMapping("/getById/{id}")
    @ApiOperation(value = "获得某个用户的详细信息")
    public Result getById(@PathVariable("id") int id) {
        User user = userService.getById(id);
        return Result.buildSuccess(user);
    }
}
