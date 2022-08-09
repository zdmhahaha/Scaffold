package com.smartj.web.controller;

import com.smartj.web.common.exception.BizException;
import com.smartj.web.common.result.ResultAble;
import com.smartj.web.common.result.ResultBody;
import com.smartj.web.request.LoginBody;
import com.smartj.web.service.UserService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

import static com.smartj.web.config.UserRealm.usernameAndPassword;

@RestController
@RequestMapping("user")
public class LoginController {

    private UserService userService;
    public static final int hashAlgorithm = 10;
    public static String salt = "";

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @ResultBody
    public String login(@Validated @RequestBody LoginBody loginBody) {
        userService.login(loginBody);
        return null;
    }

    @PostMapping("/register")
    @ResultBody
    public String register(@RequestBody Map<String, String> registerParam) {
        if (usernameAndPassword.containsKey(registerParam.get("username"))) {
            throw new BizException("用户名已存在");
        }
        //随机生成盐值
        salt = UUID.randomUUID().toString();
        System.out.println(salt);
        //对密码进行加密
        String encryptedPassword = new Sha256Hash(registerParam.get("password"), salt, hashAlgorithm).toBase64();
        LoginBody registerBody = LoginBody.builder()
                .username(registerParam.get("username"))
                .password(encryptedPassword)
                .build();
        userService.register(registerBody);
        return null;
    }
}
