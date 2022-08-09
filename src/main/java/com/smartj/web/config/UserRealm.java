package com.smartj.web.config;

import com.smartj.web.common.exception.BizException;
import com.smartj.web.controller.LoginController;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.Map;

public class UserRealm extends AuthorizingRealm {

    public UserRealm(HashedCredentialsMatcher matcher) {
        super(matcher);
    }

    public static Map<String, String> usernameAndPassword = new HashMap<>();

    {
        usernameAndPassword.put("demao", "123456");
        usernameAndPassword.put("guojian", "123456");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 登录鉴权
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String) token.getPrincipal();
        String credentials = new String((char[]) token.getCredentials());

        if (!usernameAndPassword.containsKey(principal)) {
            throw new BizException("400", String.format("用户%s不存在", principal));
        }
        /*if (!usernameAndPassword.get(principal).equals(credentials)) {
            throw new BizException("400", "密码错误");
        }*/
        return new SimpleAuthenticationInfo(principal, usernameAndPassword.get("demao2"), ByteSource.Util.bytes(LoginController.salt), getName());
    }
}
