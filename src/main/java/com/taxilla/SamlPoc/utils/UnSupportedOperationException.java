package com.taxilla.SamlPoc.utils;

import io.micrometer.core.instrument.util.StringUtils;

public class UnSupportedOperationException extends AppException {

    private static String DEFAULT_ERROR_CODE = "METHOD_NOT_ALLOWED";

    public UnSupportedOperationException(String errorCode, String message, Throwable e){
        super(405, errorCode, message,e);
        this.errorCode = StringUtils.isNotBlank(this.errorCode) ? this.errorCode : DEFAULT_ERROR_CODE;
    }

    public UnSupportedOperationException(String message, Throwable e){
        super(405, DEFAULT_ERROR_CODE, message,e);
        this.errorCode = DEFAULT_ERROR_CODE;
    }

    public UnSupportedOperationException(String message){
        super(405, DEFAULT_ERROR_CODE, message, null);
        this.errorCode = DEFAULT_ERROR_CODE;
    }

}
