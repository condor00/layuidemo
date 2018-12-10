//package com.dwyanewang.utils;
//
//import com.dwyanewang.entity.ErrorInfo;
//import org.springframework.boot.autoconfigure.web.ErrorProperties;
//import org.springframework.boot.autoconfigure.web.ServerProperties;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.util.WebUtils;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.time.LocalDateTime;
//
///**
// * @FileName: ErrorInfoBuilder.java
// * @Description: TODO
// * @Author: Dwyanewang
// * @CreateTime: 2018/12/6 11:21
// */
//@Order(Ordered.HIGHEST_PRECEDENCE)
//@Component
//public class ErrorInfoBuilder  implements HandlerExceptionResolver, Ordered {
//    private static final String ERROR_NAME = "dwyanewang.error";
//    private ErrorProperties errorProperties;
//
//
//    public ErrorProperties getErrorProperties() {
//        return errorProperties;
//    }
//
//    public void setErrorProperties(ErrorProperties errorProperties) {
//        this.errorProperties = errorProperties;
//    }
//
//    public ErrorInfoBuilder(ServerProperties serverProperties) {
//        this.errorProperties = serverProperties.getError();
//    }
//
//    public ErrorInfo getErrorInfo(HttpServletRequest request) {
//        return getErrorInfo(request, getError(request));
//    }
//
//    ErrorInfo getErrorInfo(HttpServletRequest request, Throwable error) {
//        ErrorInfo errorInfo = new ErrorInfo();
//        errorInfo.setTime(LocalDateTime.now().toString());
//        errorInfo.setUrl(request.getRequestURL().toString());
//        errorInfo.setError(error.getMessage());
//        errorInfo.setStatusCode(getHttpStatus(request).value());
//        errorInfo.setReasonPhrase(getHttpStatus(request).getReasonPhrase());
////        errorInfo.setStackTrace(getStackTraceInfo(error, isIncludeStackTrace(request)));
//        return errorInfo;
//    }
//
//    private Throwable getError(HttpServletRequest request) {
//        Throwable error = (Throwable) request.getAttribute(ERROR_NAME);
//        if (error == null) {
//            error = (Throwable) request.getAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE);
//        }
//
//        if (error != null) {
//            while (error instanceof ServletException && error.getCause() != null) {
//                error = error.getCause();
//            }
//        } else {
//            String message = (String) request.getAttribute(WebUtils.ERROR_MESSAGE_ATTRIBUTE);
//            if (StringUtils.isEmpty(message)) {
//                HttpStatus status = getHttpStatus(request);
//                message = "Unknown Exception But " + status.value() + " " + status.getReasonPhrase();
//            }
//            error = new Exception(message);
//        }
//        return error;
//    }
//
//
//    private HttpStatus getHttpStatus(HttpServletRequest request) {
//        Integer statusCode = (Integer) request.getAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE);
//        try {
//            return statusCode != null ? HttpStatus.valueOf(statusCode) : HttpStatus.INTERNAL_SERVER_ERROR;
//        } catch (Exception e) {
//            return HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//    }
//
//    private String getStackTraceInfo(Throwable error, boolean flag) {
//        if (!flag) {
//            return "omitted";
//        }
//        StringWriter stackTrace = new StringWriter();
//        error.printStackTrace(new PrintWriter(stackTrace));
//        stackTrace.flush();
//        return stackTrace.toString();
//    }
//
//    private boolean isIncludeStackTrace(HttpServletRequest request) {
//        ErrorProperties.IncludeStacktrace includeStacktrace = errorProperties.getIncludeStacktrace();
//        if (includeStacktrace == ErrorProperties.IncludeStacktrace.ALWAYS) {
//            return true;
//        }
//        if (includeStacktrace == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
//            String parameter = request.getParameter("trace");
//            return parameter != null && !"false".equals(parameter.toLowerCase());
//        }
//        return false;
//    }
//
//    @Override
//    public int getOrder() {
//        return Ordered.HIGHEST_PRECEDENCE;
//    }
//
//    @Override
//    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
//        httpServletRequest.setAttribute(ERROR_NAME, e);
//        return null;
//    }
//}
