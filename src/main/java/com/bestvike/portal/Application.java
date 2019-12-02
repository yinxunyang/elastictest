package com.bestvike.portal;

import com.bestvike.commons.properties.RestProfileProperties;
import com.bestvike.commons.redis.Cache;
import com.bestvike.portal.config.JacksonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = {"com.bestvike"})
@ServletComponentScan(basePackages = {"com.bestvike"})
@EnableConfigurationProperties({RestProfileProperties.class})
public class Application {

    @Autowired
    private JacksonMapper jacksonMapper;

    ///@LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(jacksonMapper);
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }
    @Bean
    public Cache cache() {
        return new Cache();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
