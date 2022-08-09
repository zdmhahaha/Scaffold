package com.smartj.web.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PersonParam {
    @NotEmpty(message = "姓名不能为空")
    String name;
    @NotNull(message = "年龄不能为空")
    Integer age;
    boolean male;
}
