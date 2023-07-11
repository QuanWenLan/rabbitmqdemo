package com.lanwq.helloworldconsumer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Vin lan
 * @className RabbitConfig
 * @description
 * @createTime 2022-05-30  10:03
 **/
@Configuration
public class RabbitConfig {

    public static final String HELLO_WORLD_QUEUE_NAME = "hello_world_queue";

    @Bean
    Queue testQueue() {
        return new Queue(HELLO_WORLD_QUEUE_NAME, true, false, false);
    }
}
