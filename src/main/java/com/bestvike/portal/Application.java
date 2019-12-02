package com.bestvike.portal;

import com.bestvike.commons.properties.RestProfileProperties;
import com.bestvike.commons.redis.Cache;
import com.bestvike.portal.config.JacksonMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableRedisHttpSession
//@EnableZuulProxy
//@EnableEurekaClient
@ComponentScan(basePackages = {"com.bestvike"})
@ServletComponentScan(basePackages = {"com.bestvike"})
@EnableConfigurationProperties({RestProfileProperties.class})
// @EnableConfigurationProperties({RedisClusterConfig.class, RedisClusterPoolConfig.class})
public class Application {

    //Exchange name
    @Value("${app.mq.exchange:}")
    public String DEFAULT_EXCHANGE;

    //Queue name
    @Value("${app.mq.queues.data}")
    public String QUEUE_NAME;

    //Routing key
    @Value("${app.mq.routes.data}")
    public String ROUTING_KEY;

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

    //信道配置
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(DEFAULT_EXCHANGE, true, false);
    }

    @Bean
    public Queue queue(){
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(defaultExchange()).with(ROUTING_KEY);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
