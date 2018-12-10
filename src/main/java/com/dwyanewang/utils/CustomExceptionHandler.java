package com.dwyanewang.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @FileName: CustomExceptionHandler.java
 * @Description: TODO
 * @Author: Dwyanewang
 * @CreateTime: 2018/12/6 15:24
 */
@RestControllerAdvice
public class CustomExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    Object handleExcption(Exception e, HttpServletRequest request) {
        LOGGER.error("{\"url\": {}',\"statusCode\": {},\"params\": {},\"msg\": {}}", request.getRequestURL(), getHttpStatus(request).value(), request.getQueryString(), e.getMessage());
        Map<String, Object> map = new HashMap<>(16);
        map.put("url", request.getRequestURL());
        map.put("statusCode", getHttpStatus(request).value());
        map.put("params", request.getQueryString());
        map.put("msg", e.getMessage());


        return map;
    }


    private HttpStatus getHttpStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE);
        try {
            return statusCode != null ? HttpStatus.valueOf(statusCode) : HttpStatus.INTERNAL_SERVER_ERROR;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
