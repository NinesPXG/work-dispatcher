package com.pxg.dispatcher.core.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JsonUtil {

    private static ObjectMapper mapper;

    @SneakyThrows
    public static <T> String writeJson(T data) {
        return mapper.writeValueAsString(data);
    }

    @SneakyThrows
    public static <T> T readValue(String json, Class<T> tClass) {
        return mapper.readValue(json, tClass);
    }

    @SneakyThrows
    public static <T> T readValue(String json, TypeReference<T> reference) {
        return mapper.readValue(json, reference);
    }

    public static <T> T convertValue(Object value, Class<T> tClass) {
        return mapper.convertValue(value, tClass);
    }

    public static <T> T convertValue(Object value, TypeReference<T> reference) {
        return mapper.convertValue(value, reference);
    }


    public static ObjectMapper getObjectMapper() {
        return mapper;
    }

    public static void setObjectMapper(ObjectMapper mapper) {
        JsonUtil.mapper = mapper;
    }

    static {
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        mapper.registerModule(javaTimeModule);
    }
}
