package com.taxilla.SamlPoc.utils;

import io.micrometer.core.instrument.util.StringUtils;

public class ForbiddenAccessException extends AppException {

    private ForbiddenAccessException(){

    }

    private static String DEFAULT_ERROR_CODE = "FORBIDDEN_ACCESS";

    public ForbiddenAccessException(String errorCode, String message, Throwable e){
        super(403,errorCode, message,e);
        this.errorCode = StringUtils.isNotBlank(this.errorCode) ? this.errorCode : DEFAULT_ERROR_CODE;
    }
}
