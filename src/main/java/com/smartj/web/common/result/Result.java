package com.smartj.web.common.result;

import lombok.Data;

import java.io.Serializable;


/**
 * 结果类 Result
 * 接口或者方法返回时的公共类。
 * 包含了返回码，返回消息以及数据
 *
 * @param <D> 数据主体的类型
 */
@Data
public class Result<D> implements Serializable {
    /**
     * code：返回码
     * 一般情况下，消息返回的时候，根据返回码判断是否正常返回
     * 主要用于逻辑判断
     */
    private String code;

    /**
     * message:消息
     * 当消息返回时，message 是对成功或者失败的描述
     * 主要用于展示，比如 处理失败时返回，message：数据库异常。
     */
    private String message;
    private D data;

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(String code, String message, D data) {
        this.code = "0";
        this.message = "操作成功";
        this.data = data;
    }
}
