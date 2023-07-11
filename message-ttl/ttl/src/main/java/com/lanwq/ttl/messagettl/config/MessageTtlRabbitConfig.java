package com.lanwq.ttl.messagettl.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@Configuration
public class MessageTtlRabbitConfig {
    public static final String JAVABOY_MESSSAGE_DELAY_QUEUE_NAME = "javaboy_messsage_delay_queue_name";
    public static final String JAVABOY_MESSSAGE_DELAY_EXCHANGE_NAME = "javaboy_messsage_delay_exchange_name";

    @Bean
    Queue messageDelayQueueInMsg() {
        return new Queue(JAVABOY_MESSSAGE_DELAY_QUEUE_NAME, true, false, false);
    }

    @Bean
    DirectExchange messageDelayExchangeInMsg() {
        return new DirectExchange(JAVABOY_MESSSAGE_DELAY_EXCHANGE_NAME, true, false);
    }

    @Bean
    Binding messageDelayQueueBindingInMsg() {
        return BindingBuilder.bind(messageDelayQueueInMsg())
                .to(messageDelayExchangeInMsg())
                .with(JAVABOY_MESSSAGE_DELAY_QUEUE_NAME);
    }
}
