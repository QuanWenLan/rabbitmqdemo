package com.lanwq.pubsubconsumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: rabbitmqdemo->DirectExchangeConfig
 * @description:
 * @author: lanwenquan
 * @date: 2022-06-02 17:56
 */

@Configuration
public class DirectExchangeConfig {

    public static final String DIRECT_EXCHANGE_NAME = "direct_name";
    public static final String DIRECT_QUEUE_NAME = "direct_queue_name";

    @Bean
    Queue directQueue() {
        return new Queue(DIRECT_QUEUE_NAME, true, false, false);
    }

    /**
     * DirectExchange 的路由策略是将消息队列绑定到一个 DirectExchange 上，
     * 当一条消息到达 DirectExchange 时会被转发到与该条消息 routing key 相同的 Queue 上，例如消息队列名为 “hello-queue”，
     * 则 routingkey 为 “hello-queue” 的消息会被该消息队列接收。
     * @return
     */
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE_NAME, true, false);
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(directQueue())
                .to(directExchange())
                .with(DIRECT_QUEUE_NAME);
    }
}
