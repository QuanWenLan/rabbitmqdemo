package com.lanwq.workqueueconsumer.ttlconfig;

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

    public static final String WORK_QUEUE_NAME = "work_queue";

    @Bean
    Queue testQueue() {
        return new Queue(WORK_QUEUE_NAME, true, false, false);
    }
}
