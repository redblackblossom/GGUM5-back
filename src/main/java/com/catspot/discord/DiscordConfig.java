package com.catspot.discord;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DiscordConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
