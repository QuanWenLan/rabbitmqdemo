package com.lanwq.dlxdemo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vin
 */
@Configuration
public class RabbitConfig {
    public static final String MSG_EXCHANGE_NAME = "msg_exchange_name";
    public static final String MSG_QUEUE_NAME = "msg_queue_name";

    @Bean
    DirectExchange msgDirectExchange() {
        return new DirectExchange(MSG_EXCHANGE_NAME, true, false);
    }

    @Bean
    Queue msgQueue() {
        Map<String, Object> args = new HashMap<>();
        //设置消息过期时间，消息过期之后吧，立马就会进入到死信队列中
        args.put("x-message-ttl", 0);
        //指定死信队列的交换机
        args.put("x-dead-letter-exchange", DLXConfig.DLX_EXCHANGE_NAME);
        //指定死信队列路由的 key
        args.put("x-dead-letter-routing-key", DLXConfig.DLX_ROUTING_KEY);
        return new Queue(MSG_QUEUE_NAME, true, false, false, args);
    }

    @Bean
    Binding msgBinding() {
        return BindingBuilder.bind(msgQueue())
                .to(msgDirectExchange())
                .with(MSG_QUEUE_NAME);
    }
}
