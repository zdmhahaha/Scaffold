package com.smartj.web.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.smartj.web.controller.LoginController.hashAlgorithm;

@Configuration
public class ShiroConfig {

    @Bean
    public HashedCredentialsMatcher matcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashIterations(hashAlgorithm);
        matcher.setHashAlgorithmName("SHA-256");
        matcher.setStoredCredentialsHexEncoded(false);
        return matcher;
    }

    @Bean(name = "Realm")
    public UserRealm getRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
        return new UserRealm(hashedCredentialsMatcher);
    }

    @Bean(name =  "DefaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("Realm") UserRealm  userRealm) {
        DefaultWebSecurityManager  securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("DefaultWebSecurityManager")DefaultWebSecurityManager  securityManager) {

        ShiroFilterFactoryBean  shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器，关联一个securityManager，也是通过Qualifier注解拿到
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加Shiro过滤拦截器
        /**
         *常用过滤器
         *   anon：无需登录（认证）可以访问
         *   authc：必须认证才可以访问
         *   user：如果使用remeberMe的功能，可以直接访问
         *   perms:该资源必须得到资源权限才可以访问
         *   role:该资源必须得到角色权限才可以访问
         */
        Map<String, String> filterMap =  new LinkedHashMap<>();
        //需要过滤的东西,不拦截的放前面
        filterMap.put("/user/**",  "anon");
        filterMap.put("/**",  "authc");

        //修改拦截后跳转的页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

}
