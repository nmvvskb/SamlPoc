package com.taxilla.SamlPoc.utils;

import java.util.Map;

public class AppException  extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public static String DEFAULT_ERR_CODE = "SERVER_ERROR";//Generic exception that gets thrown when it need not be handled

    protected int httpCode = 500;
    protected String errorCode = DEFAULT_ERR_CODE;
    protected Map<String,Object> errorResposne;

    protected AppException(){
    }

    protected AppException(Throwable e) {
        super(e);
        if(e instanceof AppException)
            this.setStackTrace( new StackTraceElement[0]);
    }

    protected AppException(String message) {
        this(message, null);
    }

    protected AppException(String message, Throwable e) {
        super(message,e);
        if(e instanceof AppException)
            this.setStackTrace( new StackTraceElement[0]);
    }

    protected AppException(String errorCode, String message, Throwable e) {
        this(message, e);
        if(e instanceof AppException){
            this.errorCode =  ((AppException) e).getErrorCode();
            this.httpCode =  ((AppException) e).getHttpCode();
        } else {
            this.errorCode = errorCode;
        }
    }

    protected AppException(int httpCode, String errorCode, String message, Throwable e) {
        this(errorCode, message, e);
        if(!(e instanceof AppException)){
            this.httpCode = httpCode;
        }
    }

    protected AppException(int httpCode, String errorCode, String message) {
        this(message);
        this.httpCode = httpCode;
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public String getWrappedMessage(){
        if(getCause() instanceof AppException){
            return ((AppException) getCause()).getWrappedMessage();
        }else{
            return getMessage();
        }
    }

    public Map<String, Object> getErrorResposne() {
        return errorResposne;
    }

    public void setErrorResposne(Map<String, Object> errorResposne) {
        this.errorResposne = errorResposne;
    }
}
