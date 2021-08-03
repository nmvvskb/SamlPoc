package com.taxilla.SamlPoc.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.DelegatingDeserializer;

import java.io.IOException;

public class PostConstructDeserializer extends DelegatingDeserializer {

    private static final long serialVersionUID = 1L;

    private final JsonDeserializer<?> deserializer;

    public PostConstructDeserializer(JsonDeserializer<?> deserializer) {
        super(deserializer);
        this.deserializer = deserializer;
    }

    @Override
    protected JsonDeserializer<?> newDelegatingInstance(JsonDeserializer<?> newDelegatee) {
        return deserializer;
    }

    @Override
    public Object deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        Object result = _delegatee.deserialize(jp, ctxt);
        if (result instanceof PostConstructor) {
            ((PostConstructor) result).postConstruct();
        }
        return result;
    }
}
