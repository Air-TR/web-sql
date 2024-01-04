package com.huibo.websql.config;

import com.huibo.websql.exception.BusinessException;
import com.huibo.websql.result.Result;
import com.huibo.websql.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: TR
 * @Date: 2023/4/13
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handle(Exception e) {
        log.error("[系统异常]", e);
        return Result.fail(ResultEnum.FAIL.getCode(), e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result handle(BusinessException businessException) {
        return Result.fail(businessException.getCode(), businessException.getMessage());
    }

}
