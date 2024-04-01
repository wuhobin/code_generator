package com.boot.example.exception;

import cn.dev33.satoken.exception.NotLoginException;
import com.boot.example.utils.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局异常拦截
 * @author wuhongbin
 * @ClassName GlobalExceptionHandler
 * @description: TODO
 * @date 2024年03月29日
 * @version: 1.0.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 全局异常拦截（拦截项目中的NotLoginException异常）
    @ExceptionHandler(NotLoginException.class)
    public CommonResult handlerNotLoginException(NotLoginException nle)
            throws Exception {
        // 判断场景值，定制化异常信息
        String message = "";
        if(nle.getType().equals(NotLoginException.NOT_TOKEN)) {
            message = "未能读取到有效 token";
        }
        else if(nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
            message = "token 无效";
        }
        else if(nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            message = "token 已过期";
        }
        else if(nle.getType().equals(NotLoginException.BE_REPLACED)) {
            message = "token 已被顶下线";
        }
        else if(nle.getType().equals(NotLoginException.KICK_OUT)) {
            message = "token 已被踢下线";
        }
        else if(nle.getType().equals(NotLoginException.TOKEN_FREEZE)) {
            message = "token 已被冻结";
        }
        else if(nle.getType().equals(NotLoginException.NO_PREFIX)) {
            message = "未按照指定前缀提交 token";
        }
        else {
            message = "当前会话未登录";
        }

        // 返回给前端
        return CommonResult.failed(message);
    }


    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public CommonResult<?> handlerValidException(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String message = fieldErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("；"));
        return CommonResult.failed(400, message);
    }


    @ExceptionHandler(CustomException.class)
    public CommonResult customException(CustomException e){
        return CommonResult.failed(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public CommonResult exception(Exception e){
        log.error(e.getMessage(), e);
        return CommonResult.failed(500, e.getMessage());
    }



}
