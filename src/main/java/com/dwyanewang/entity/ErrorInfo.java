package com.dwyanewang.entity;

/**
 * @FileName: ErrorInfo.java
 * @Description: 统一异常处理实体类
 * @Author: Dwyanewang
 * @CreateTime: 2018/12/6 11:16
 */
public class ErrorInfo {
    /**
     * 发生时间
     */
    private String time;
    /**
     * 访问url
     */
    private String url;
    /**
     * 错误类型
     */
    private String error;
    /**
     * 错误的堆栈轨迹
     */
    private String stackTrace;
    /**
     * 状态码
     */
    private int statusCode;
    /**
     * 状态码-描述
     */
    private String reasonPhrase;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }

    @Override
    public String toString() {
        return "ErrorInfo{" +
                "time='" + time + '\'' +
                ", url='" + url + '\'' +
                ", error='" + error + '\'' +
                ", stackTrace='" + stackTrace + '\'' +
                ", statusCode=" + statusCode +
                ", reasonPhrase='" + reasonPhrase + '\'' +
                '}';
    }
}
