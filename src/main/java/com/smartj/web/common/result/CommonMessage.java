package com.smartj.web.common.result;

import com.smartj.web.common.result.MessageInfo;

/**
 * 全局常规消息
 * 定义了全局常规的成功或者错误码以及对应的消息
 */
public enum CommonMessage implements MessageInfo {

    SUCCESS("200", "成功!"),
    BODY_NOT_MATCH("400", "请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH("401", "请求的数字签名不匹配!"),
    NOT_FOUND("404", "未找到该资源!"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),
    SERVER_BUSY("503", "服务器正忙，请稍后再试!"),
    SYS_ERR("1000", "系统异常!"),
    PARAM_ERR("2000", "参数异常!");


    /**
     * 错误码
     */
    private String code;

    /**
     * 错误描述
     */
    private String message;

    CommonMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}