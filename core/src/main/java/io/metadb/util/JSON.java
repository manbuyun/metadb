package io.metadb.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

/**
 * @author jinhai
 * @date 2021/11/07
 */
public class JSON {
    public static final ObjectMapper MAPPER = JsonMapper.builder().build();

    public static <T> String encode(T value) {
        try {
            return MAPPER.writeValueAsString(value);
        } catch (JacksonException e) {
            throw new RuntimeException("JSON encode error", e);
        }
    }

    public static <T> T decode(String value, Class<T> clazz) {
        try {
            return MAPPER.readValue(value, clazz);
        } catch (JacksonException e) {
            throw new RuntimeException("JSON decode error", e);
        }
    }
}
