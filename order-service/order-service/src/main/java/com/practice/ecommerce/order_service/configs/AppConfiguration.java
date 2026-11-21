package com.practice.ecommerce.order_service.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class AppConfiguration {
    @Bean
    public ModelMapper getModalMapper(){
        return new ModelMapper();
    }

}
