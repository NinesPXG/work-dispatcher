package com.pxg.demo.config;

import com.alibaba.cola.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Response handle(Throwable e, HttpServletRequest request, HttpServletResponse response) {

        if (e instanceof HttpMessageNotReadableException) {
            return Response.buildFailure(Const.ERROR, "请求错误");
        } else if (e instanceof MissingServletRequestParameterException) {
            String errMsg = MessageFormat.format("缺少参数{0}", ((MissingServletRequestParameterException) e).getParameterName());
            return Response.buildFailure(Const.ERROR, errMsg);
        } else if (e instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) e).getConstraintViolations();
            String errMsg = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
            return Response.buildFailure(Const.ERROR, errMsg);
        } else if (e instanceof BindException) {
            List<ObjectError> errors = (((BindException) e).getAllErrors());
            String errMsg = errors.stream().map(error -> String.format("%s:%s", ((FieldError) error).getField(), error.getDefaultMessage())).collect(Collectors.joining(";"));
            return Response.buildFailure(Const.ERROR, errMsg);
        }
        log.error(e.getMessage());
        return Response.buildFailure(Const.ERROR, "未知错误，请联系管理员");
    }

}
