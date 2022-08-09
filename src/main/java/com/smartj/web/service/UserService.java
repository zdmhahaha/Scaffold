package com.smartj.web.service;

import com.smartj.web.request.LoginBody;

public interface UserService {

    void login(LoginBody loginBody);

    void register(LoginBody registerBody);
}
