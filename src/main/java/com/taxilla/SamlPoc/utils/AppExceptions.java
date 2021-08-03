package com.taxilla.SamlPoc.utils;

public class AppExceptions {


    private static String getWrappedMessage(Throwable e){
        return e instanceof AppException ? ((AppException)e).getWrappedMessage() : null;
    }

    public static AppException serverError(String message){
        return new InternalServerError(null, message,null);
    }

    public static AppException serverError(String message, Throwable e){
        return new InternalServerError(null, message, e);
    }

    public static AppException unauthorized(String message){
        return new UnauthorizedException(message);
    }

    public static AppException unauthorized(String message, String errorCode){
        return new UnauthorizedException(message, errorCode);
    }

    public static AppException unsupportedOpertion(String message){
        return new UnSupportedOperationException(message);
    }

    public static AppException forbiddenAccess(String message){
        return new ForbiddenAccessException(null, message, null);
    }

    public static AppException badRequest(String errorCode, String message){
        return new BadRequestException(errorCode, message, null, null);
    }

    public static AppException badRequest(String message, Throwable e){
        return new BadRequestException(null, message, e, null);
    }

    public static AppException badRequest(String message){
        return new BadRequestException(message);
    }

    public static AppException resourceExists(String message){
        return new ResourceExistsException(message);
    }

    public static AppException resourceNotExists(String message){
        return new ResourceNotExistsException(message);
    }
}
