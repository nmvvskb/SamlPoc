package com.taxilla.SamlPoc.utils;

public class ResourceExistsException extends AppException{
    private static String DEFAULT_ERROR_CODE = "RESOURCE_EXISTS";

    public ResourceExistsException(String message) {
        super(409, DEFAULT_ERROR_CODE, message, null);
    }
}
