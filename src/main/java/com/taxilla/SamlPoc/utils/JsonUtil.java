package com.taxilla.SamlPoc.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JsonUtil {
    public static ObjectMapper mapper = new ApplicationObjectMapper();
    public static ObjectMapper prettyMapper = new ApplicationObjectMapper();

    static {
        prettyMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        prettyMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    public static String encode(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw AppExceptions.serverError("Failed to encode as JSON: " + e.getMessage(), e);
        }
    }

    public static byte[] encodeToBytes(Object obj) {
        try {
            return mapper.writeValueAsBytes(obj);
        } catch (Exception e) {
            throw AppExceptions.serverError("Failed to encode as JSON: " + e.getMessage(), e);
        }
    }


    public static String encodePrettily(Object obj) {
        try {
            return prettyMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw AppExceptions.serverError("Failed to encode as JSON: " + e.getMessage(), e);
        }
    }

    public static <T> T decodeValue(String str, Class<T> clazz) {
        try {
            return mapper.readValue(str, clazz);
        } catch (Exception e) {
            throw AppExceptions.serverError("Failed to decode:" + e.getMessage(), e);
        }
    }

    public static <T> T decodeValue(byte[] bytes, Class<T> clazz) {
        try {
            return mapper.readValue(bytes, clazz);
        } catch (Exception e) {
            throw AppExceptions.serverError("Failed to decode:" + e.getMessage(), e);
        }
    }


    public static <E> List<E> decodeList(String str, Class<E> clazz) {
        return decodeCollection(str, List.class, clazz);
    }

    public static <E> List<E> decodeList(InputStream str, Class<E> clazz) {
        return decodeCollection(str, List.class, clazz);
    }

    public static <E> Set<E> decodeSet(String str, Class<E> clazz) {
        return decodeCollection(str, Set.class, clazz);
    }

    public static <E> Set<E> decodeSet(InputStream str, Class<E> clazz) {
        return decodeCollection(str, Set.class, clazz);
    }

    private static <C extends Collection, E> C decodeCollection(String str, Class<? extends Collection> collectionType, Class<E> elementClass) {
        try {
            CollectionType javaType = mapper.getTypeFactory().constructCollectionType(collectionType, elementClass);
            return mapper.readValue(str, javaType);
        } catch (Exception e) {
            throw AppExceptions.serverError("Failed to decode:" + e.getMessage(), e);
        }
    }

    private static <C extends Collection, E> C decodeCollection(InputStream str, Class<? extends Collection> collectionType, Class<E> elementClass) {
        try {
            CollectionType javaType = mapper.getTypeFactory().constructCollectionType(collectionType, elementClass);
            return mapper.readValue(str, javaType);
        } catch (Exception e) {
            throw AppExceptions.serverError("Failed to decode:" + e.getMessage(), e);
        }
    }

    public static <T> T decode(String s, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(s, typeReference);
        } catch (IOException e) {
            throw AppExceptions.serverError("Unable to deserialize json", e);
        }
    }

    public static JsonNode readTree(JsonParser p) {
        try {
            return mapper.readTree(p);
        } catch (IOException e) {
            throw AppExceptions.serverError("Unable to read json", e);
        }
    }

    public static Map<String,Object> getNodeAsMap(JsonNode node) {
        return mapper.convertValue(node, Map.class);
    }
}
