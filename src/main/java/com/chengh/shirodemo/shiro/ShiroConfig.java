package com.chengh.shirodemo.shiro;

import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/login", "anon");
        map.put("/test","authc");
        map.put("/test1","authc");
        map.put("/session","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultDefaultWebSecurityManager(UserRealm userRealm,SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm(userRealm);
//        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    /**
     * 创建Realm
     */

    @Bean(name = "userRealm")
    public UserRealm getRealm() {
        return new UserRealm();
    }

//    @Bean(name = "sessionManager")
//    public DefaultWebSessionManager getDefaultWebSessionManager() {
//        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
//        defaultWebSessionManager.setGlobalSessionTimeout(1000 * 60);// 会话过期时间，单位：毫秒(在无操作时开始计时)--->一分钟,用于测试
//        defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
//        defaultWebSessionManager.setSessionIdCookieEnabled(true);
//        return defaultWebSessionManager;
//    }


}
