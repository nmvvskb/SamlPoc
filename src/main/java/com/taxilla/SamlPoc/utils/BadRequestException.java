package com.taxilla.SamlPoc.utils;

import io.micrometer.core.instrument.util.StringUtils;

import java.util.Map;

public class BadRequestException extends AppException {

    private static String DEFAULT_ERROR_CODE = "BAD_REQUEST";

    public BadRequestException(String message){
        super(400, DEFAULT_ERROR_CODE, message, null);
    }

    public BadRequestException(String errorCode, String message, Throwable e, Map<String,Object> response){
        super(400, StringUtils.isNotBlank(errorCode) ? errorCode : DEFAULT_ERROR_CODE, message,e);
        super.errorResposne = response;
        this.errorCode = StringUtils.isNotBlank(this.errorCode) ? this.errorCode : DEFAULT_ERROR_CODE;
    }
}
