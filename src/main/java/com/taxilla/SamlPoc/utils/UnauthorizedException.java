package com.taxilla.SamlPoc.utils;

import io.micrometer.core.instrument.util.StringUtils;

public class UnauthorizedException extends AppException {
    private static String DEFAULT_ERROR_CODE = "UNAUTHORIZED_ACCESS";

    public UnauthorizedException(String message){
        super(401, DEFAULT_ERROR_CODE, message, null);
        this.errorCode = StringUtils.isNotBlank(this.errorCode) ? this.errorCode : DEFAULT_ERROR_CODE;
    }

    public UnauthorizedException(String errorCode, String message){
        super(401, errorCode, message);
        this.errorCode = StringUtils.isNotBlank(this.errorCode) ? this.errorCode : DEFAULT_ERROR_CODE;
    }
}
