package com.payments.services.converters;

public interface JsonConverter {
    <T> String toJson(T object, Class<T> type);

    <T> T fromJson(String json, Class<T> type);
}
