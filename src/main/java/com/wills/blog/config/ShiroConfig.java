package com.wills.blog.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * ShiroFilterFactoryBean
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置安全管理器d
        bean.setSecurityManager(defaultWebSecurityManager);
        // 添加shiro的内置过滤器
        /**
         * anon 无需认证就可以访问
         * authc 必须认证了才能访问
         * user 必须拥有记住我才能使用
         * perms 必须拥有对某个资源的访问权限才能访问
         * role 拥有某个角色权限
         * perms[user:add]
         */
        // filter内是配置需要权限认证的页面
        Map<String,String> filter = new LinkedHashMap<>();
        filter.put("/user/add","authc");
        bean.setFilterChainDefinitionMap(filter);
        // 设置登陆页面
        bean.setLoginUrl("/");
        return bean;
    }

    /**
     * DefaultWebSecurityManager
     * @return
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager dfw = new DefaultWebSecurityManager();
        dfw.setRealm(userRealm);
        return dfw;
    }

    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }
}
