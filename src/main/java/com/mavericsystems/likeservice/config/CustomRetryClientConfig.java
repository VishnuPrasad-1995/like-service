package com.mavericsystems.likeservice.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomRetryClientConfig{

    @Bean
    public Retryer clientServiceRetryer(){
        return new FeignClientRetryer();
    }


}
