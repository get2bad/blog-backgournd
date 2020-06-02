package com.wills.blog.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private int redisPort;

    /**
     * ShiroFilterFactoryBean
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置安全管理器d
        bean.setSecurityManager(securityManager);
        // 设置登陆页面
        bean.setLoginUrl("/user/not_login");
        bean.setUnauthorizedUrl("/user/not_permit");
        // 添加shiro的内置过滤器
        /**
         * anon 无需认证就可以访问
         * authc 必须认证了才能访问
         * user 必须拥有记住我才能使用
         * perms 必须拥有对某个资源的访问权限才能访问
         * role 拥有某个角色权限
         * perms[user:add]
         */
//        // filter内是配置需要权限认证的页面
//        Map<String,String> filter = new LinkedHashMap<>();
//        filter.put("/user/getAll","authc");
//        bean.setFilterChainDefinitionMap(filter);
//        // 自定义Fileter
//        Map<String, Filter> customFilter = new LinkedHashMap<>();
//        customFilter.put("loginFilter",new CustomAuthenticationFilter());
//        bean.setFilters(customFilter);
        return bean;
    }

    /**
     * DefaultWebSecurityManager
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager d = new DefaultWebSecurityManager();
        d.setRealm(userRealm());
        d.setSessionManager(sessionManager());
        // 权限缓存管理器
        d.setCacheManager(redisCacheManager());
        return d;
    }

    /**
     * 自定义userRealm
     * @return
     */
    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }

    /**
     * 用户会话保持 存放在redis中，重启后端，前端用户无感知，继续保持在登录状态
     * @return
     */
    @Bean
    public SessionManager sessionManager(){
        UserSessionManager u = new UserSessionManager();
        u.setCacheManager(redisCacheManager());
        // 设置过期时间 单位：毫秒 设置为1小时
        u.setGlobalSessionTimeout(1000*60*60);
        u.setSessionDAO(sessionDAO());
        return u;
    }

    /**
     * SessionDAO <----> 前后端分离重写SessionId的生成方式,使用JWT的模式
     * @return
     */
    @Bean
    public SessionDAO sessionDAO(){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setSessionIdGenerator(new JWTSessionIdGenerator());
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * 将角色权限等内容放入Redis，设置权限的缓存时间
     * @return
     */
    @Bean
    public RedisCacheManager redisCacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        // 单位： 秒 过期时间
        redisCacheManager.setExpire(60);
        return redisCacheManager;
    }

    /**
     * 注入RedisManager
     * @return
     */
    @Bean
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisHost);
        redisManager.setPort(redisPort);
        return redisManager;
    }

    /**
     * 注解生效器
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 密码加密的服务
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher h = new HashedCredentialsMatcher();
        // md5加密
        h.setHashAlgorithmName("md5");
        // 单重md5
        h.setHashIterations(1);
        return h;
    }

}
