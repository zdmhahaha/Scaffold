package com.smartj.web.common.result;


import cn.hutool.json.JSONUtil;

public class ResultUtil<T> {

    public static <T> Result<T> success(T data) {
        return new Result<>("0", "成功", data);
    }

    public static Result success() {
        return new Result("0", "成功");
    }

    public static String successStr(Object data) {

        return JSONUtil.toJsonStr(ResultUtil.success(data));
    }

    public static <T> Result<T> error(MessageInfo messageInfo) {
        return new Result<>(messageInfo.getCode(), messageInfo.getMessage());
    }

    public static <T> Result<T> error(Exception e) {
        if (e instanceof MessageInfo ){
            MessageInfo messageInfo = (MessageInfo) e;
          return   new Result<>(messageInfo.getCode(), messageInfo.getMessage());
        }
        return new Result<>(CommonMessage.SYS_ERR.getCode(), e.getMessage());
    }

    public static <T> Result<T> error(String code, String message) {
        return new Result<>(code, message);
    }
}
