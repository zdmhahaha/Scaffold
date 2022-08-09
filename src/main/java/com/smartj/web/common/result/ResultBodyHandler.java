package com.smartj.web.common.result;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.AnnotatedElement;
import java.util.Objects;

/**
 * <h1>@ControllerAdvice</h1>
 * 自定义拦截规则：
 * <p>
 * ControllerAdvice 提供了多种指定Advice规则的定义方式
 * <p>
 * 匹配全部，默认什么都不写，则是Advice所有Controller<BR/>
 * 匹配包：<BR/>
 * 匹配某个包写成@ControllerAdvice("org.my.pkg") 或者@ControllerAdvice(basePackages="org.my.pkg"),则匹配org.my.pkg包及其子包下的所有Controller<BR/>
 * 匹配一个包的数组,如：@ControllerAdvice(basePackages={"org.my.pkg", "org.my.other.pkg"})<BR/>
 * 按注解匹配：自定了一个 @CustomAnnotation 注解，匹配所有被这个注解修饰的 Controller,这么写：@ControllerAdvice（annotations={CustomAnnotation.class})<BR/>
 * </P>
 */
@ControllerAdvice
public class ResultBodyHandler implements ResponseBodyAdvice<Object> {
    public ResultBodyHandler() {
    }

    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        AnnotatedElement annotatedElement = returnType.getAnnotatedElement();
        ResultBody resultBody = annotatedElement.getAnnotation(ResultBody.class);
        return Objects.nonNull(resultBody);
    }

    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Class<?> returnType = Objects.requireNonNull(methodParameter.getMethod()).getReturnType();
        if (returnType.equals(String.class)) {
            return ResultUtil.successStr(body);
        } else {
            body = null == body ? ResultUtil.success() : body;
            return body instanceof Result ? (Result) body : ResultUtil.success(body);
        }
    }
}
