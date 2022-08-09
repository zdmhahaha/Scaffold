package com.smartj.web.common.result;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * 切面日志记录
 *
 * @author cw
 */
@Aspect
@Component
public class ResultInfoAop {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(com.smartj.web.common.result.ResultAble)")
    public void cutController() {
    }


    @Around("cutController()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        RequestContextHolder.setRequestAttributes(requestAttributes, true);
        Object proceed;
        try {
            proceed = point.proceed();
        } catch (Exception e) {
            log.error(point.getSignature().getName() + ":", e);
            proceed = ResultUtil.error(e);
        }
        return proceed;
    }



}