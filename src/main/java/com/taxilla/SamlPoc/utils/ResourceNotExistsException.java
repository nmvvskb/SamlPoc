package com.taxilla.SamlPoc.utils;

public class ResourceNotExistsException extends AppException{
    private static String DEFAULT_ERROR_CODE = "RESOURCE_NOT_FOUND";

    public ResourceNotExistsException(String message) {
        super(404, DEFAULT_ERROR_CODE, message, null);
    }
}
