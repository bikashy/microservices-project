package com.practice.ecommerce.inventory_service.appConfig;

import lombok.Builder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ConfigurationClass {
    @Bean
public ModelMapper getModalMapper(){
    return new ModelMapper();
}

@Bean
public RestClient getRestClient(){
        return RestClient.builder().build();
}

}
