package com.github.dmytr0.kinoreminderbot.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.List;

public class JsonParser {

    private static final ObjectMapper mapperJson = new ObjectMapper().registerModule(new JavaTimeModule());

    @SneakyThrows
    public static String prepareObjectJson(Object o) {
        return mapperJson.writeValueAsString(o);
    }

    @SneakyThrows
    public static String preparePrettyJson(Object o) {
        return mapperJson.writerWithDefaultPrettyPrinter().writeValueAsString(o);
    }

    @SneakyThrows
    public static <T> T parseObjectJson(String obj, Class<T> c) {
        return mapperJson.readValue(obj, c);
    }

    @SneakyThrows
    public static <T> List<T> parseListJson(String arr, Class<T> c) {
        return mapperJson.readValue(arr, mapperJson.getTypeFactory().constructCollectionType(List.class, c));
    }

    @SneakyThrows
    public static <T> T parseFile(URL resource, Class<T> clazz) {
        return mapperJson.readValue(resource, clazz);
    }
}
