//package com.example.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.databind.ser.BeanSerializer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//
//@Configuration
//public class EirExceptionConfig {
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @Bean
//    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
////        BeanSerializer serializer = new BeanSerializer();
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(mapper);
//        return converter;
//    }
//}
