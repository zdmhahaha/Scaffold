package com.smartj.web.service.impl;

import com.smartj.web.request.LoginBody;
import com.smartj.web.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import static com.smartj.web.config.UserRealm.usernameAndPassword;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void login(LoginBody loginBody) {
        //get Subject
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                loginBody.getUsername(),loginBody.getPassword());
        subject.login(usernamePasswordToken);
    }

    @Override
    public void register(LoginBody registerBody) {
        usernameAndPassword.put(registerBody.getUsername(),registerBody.getPassword());
    }
}
