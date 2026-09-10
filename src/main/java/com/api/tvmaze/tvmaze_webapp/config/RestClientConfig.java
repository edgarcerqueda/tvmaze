package com.api.tvmaze.tvmaze_webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    /** Se configura el cliente desde un Bean
     * en la clase de configuracion
     * */
    @Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }
}
