package com.wills.blog.config;

import com.wills.blog.bean.Permission;
import com.wills.blog.bean.Role;
import com.wills.blog.bean.User;
import com.wills.blog.service.UserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    /**
     * 在权限认证时触发
     * @param principalCollection
     * @return
     */
    @SneakyThrows
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("权限授权验证服务启动------------------>");
        User user = new User();
        // 使用BeanUtils.copyProperties
        BeanUtils.copyProperties(user,principalCollection.getPrimaryPrincipal());
        List<Role> roleList = userService.getRoleListByUserId(user.getUserId());
        Set<String> stringRoleList = new HashSet<>();
        Set<String> permissionSet = new HashSet<String>();
        for (Role role : roleList) {
            stringRoleList.add(role.getRoleName());
            List<Permission> permissionList = userService.getPermissionListByRoleId(role.getRoleId());
            for (Permission permission : permissionList) {
                permissionSet.add(permission.getPermissionName());
            }
        }
        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
        s.setRoles(stringRoleList);
        s.setStringPermissions(permissionSet);
        log.info("<------------------权限授权验证服务启动完成！");
        return s;
    }

    /**
     * 在身份认证时触发
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("正在进行身份验证------------>");
        String username = (String) authenticationToken.getPrincipal();
        User user = userService.getByUserName(username);
        log.info("<------------身份验证成功！");
        if(user !=null){
            return new SimpleAuthenticationInfo(user,user.getPassword(),this.getClass().getName());
        }else{
            return null;
        }
    }
}
