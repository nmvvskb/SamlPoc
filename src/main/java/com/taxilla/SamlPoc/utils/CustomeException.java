package com.taxilla.SamlPoc.utils;

public class CustomeException extends RuntimeException{
    CustomeException(){
        super();
    };

    public CustomeException(String message){
        super(message);
    }
}
