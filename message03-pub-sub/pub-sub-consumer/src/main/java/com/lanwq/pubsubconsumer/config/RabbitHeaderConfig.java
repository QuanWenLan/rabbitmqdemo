package com.lanwq.pubsubconsumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: rabbitmqdemo->RabbitHeaderConfig
 * @description: header 交换机
 * @author: lanwenquan
 * @date: 2022-06-02 21:25
 * HeadersExchange 是一种使用较少的路由策略，HeadersExchange 会根据消息的 Header 将消息路由到不同的 Queue 上，这种策略也和 routingkey无关
 */
@Configuration
public class RabbitHeaderConfig {
    public final static String HEADER_NAME = "lanwq-header";

    @Bean
    HeadersExchange headersExchange() {
        return new HeadersExchange(HEADER_NAME, true, false);
    }

    @Bean
    Queue queueName() {
        return new Queue("name-queue");
    }

    @Bean
    Queue queueAge() {
        return new Queue("age-queue");
    }

    @Bean
    Binding bindingName() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "sang");
        return BindingBuilder.bind(queueName())
                .to(headersExchange()).whereAny(map).match();
    }

    @Bean
    Binding bindingAge() {
        return BindingBuilder.bind(queueAge())
                .to(headersExchange()).where("age").exists();
    }
}
