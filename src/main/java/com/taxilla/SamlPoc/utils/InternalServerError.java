package com.taxilla.SamlPoc.utils;

import io.micrometer.core.instrument.util.StringUtils;

public class InternalServerError extends AppException {

    private static String DEFAULT_ERROR_CODE = "SERVER_ERROR";

    public InternalServerError(String errorCode, String message, Throwable e){
        super(500,errorCode, message,e);
        this.errorCode = StringUtils.isNotBlank(this.errorCode) ? this.errorCode : DEFAULT_ERROR_CODE;
    }
}
