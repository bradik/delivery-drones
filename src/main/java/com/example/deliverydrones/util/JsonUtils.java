package com.example.deliverydrones.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtils {
    private static final ObjectMapper CONFIGURED_MAPPER = new ObjectMapper();

    static {
        CONFIGURED_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        CONFIGURED_MAPPER.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        CONFIGURED_MAPPER.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    @SneakyThrows
    public static String objectToJson(Object dto) {
        String result = "empty";
        if (dto != null) {
            result = CONFIGURED_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(dto);
        }
        return result;
    }

    @SneakyThrows
    public static <T> T jsonToObject(String json, Class<T> tClass) {

        return CONFIGURED_MAPPER.readValue(json, tClass);
    }
}
