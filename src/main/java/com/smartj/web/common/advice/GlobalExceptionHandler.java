package com.smartj.web.common.advice;


import com.smartj.web.common.result.CommonMessage;
import com.smartj.web.common.exception.BizException;
import com.smartj.web.common.result.Result;
import com.smartj.web.common.result.ResultUtil;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 注意方法上要加上@ResponseBody 否则返回的不是json
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({BindException.class})
    @ResponseBody
    public Result<Void> bindException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        ObjectError error = bindingResult.getAllErrors().get(0);
        return ResultUtil.error(CommonMessage.PARAM_ERR.getCode(), error.getDefaultMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result<Void> bindException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().get(0);
        return ResultUtil.error(CommonMessage.PARAM_ERR.getCode(), objectError.getDefaultMessage());
    }


    /**
     * 处理自定义的业务异常
     *
     * @param e   异常
     * @return ResultInfo 对象
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public Result<Void> bizExceptionHandler( BizException e) {
        return ResultUtil.error(e.getErrorCode(), e.getErrorMsg());
    }

}
