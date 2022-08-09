package com.smartj.web.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
public class LoginBody {

    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    private String username;

    /**
     * 用户密码
     */
    @NotNull(message = "密码不能为空")
    private String password;

    /**
     * 验证码
     */
    @NotNull(message = "验证码不能为空")
    private String code;

    private String avatarUrl;

    private Date createdAt;

    private Date updatedAt;
}
