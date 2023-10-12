package com.example.demo.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.Date;

public class CustomObjectMapper extends ObjectMapper {
    public CustomObjectMapper() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Date.class, new CustomDateSerializer());

        registerModule(simpleModule);
        registerModule(new JavaTimeModule());

        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }
}
