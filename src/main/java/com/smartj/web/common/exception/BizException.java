package com.smartj.web.common.exception;


import com.smartj.web.common.result.MessageInfo;
import lombok.Data;

@Data
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected String errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    public BizException() {
        super();
    }

    public BizException(MessageInfo messageInfo) {
        super(messageInfo.getCode());
        this.errorCode = messageInfo.getCode();
        this.errorMsg = messageInfo.getMessage();
    }

    public BizException(MessageInfo messageInfo, Throwable cause) {
        super(messageInfo.getCode(), cause);
        this.errorCode = messageInfo.getCode();
        this.errorMsg = messageInfo.getMessage();
    }

    public BizException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BizException(String errorCode, String errorMsg) {
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BizException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}