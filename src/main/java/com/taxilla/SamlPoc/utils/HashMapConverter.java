package com.taxilla.SamlPoc.utils;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

public interface HashMapConverter <T, String> extends AttributeConverter<T, java.lang.String> {

    T getInstance();

    @Override
    default java.lang.String convertToDatabaseColumn(T attribute) {
        return JsonUtil.encode(attribute);
    }

    @Override
    default T convertToEntityAttribute(java.lang.String data) {
        return JsonUtil.decodeValue(data, (Class<T>)getInstance().getClass());
    }
}
