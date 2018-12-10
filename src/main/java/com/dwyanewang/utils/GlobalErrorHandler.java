//package com.dwyanewang.utils;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @FileName: GlobalErrorHandler.java
// * @Description: TODO
// * @Author: Dwyanewang
// * @CreateTime: 2018/12/6 11:25
// */
//@ControllerAdvice
//public class GlobalErrorHandler {
//    public static final Logger LOGGER = LoggerFactory.getLogger(GlobalErrorHandler.class);
//    @Autowired
//    private ErrorInfoBuilder errorInfoBuilder;
//
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public void exceptionHandler(HttpServletRequest request, Throwable error) {
//        LOGGER.error(errorInfoBuilder.getErrorInfo(request, error).toString());
//    }
//
//}
