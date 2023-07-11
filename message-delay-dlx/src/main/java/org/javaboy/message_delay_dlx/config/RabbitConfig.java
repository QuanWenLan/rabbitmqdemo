package org.javaboy.message_delay_dlx.config;

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
 * 假如一条消息需要延迟 30 分钟执行，我们就设置这条消息的有效期为 30 分钟，同时为这条消息配置死信交换机和死信 routing_key，
 * 并且不为这个消息队列设置消费者，那么 30 分钟后，这条消息由于没有被消费者消费而进入死信队列，
 * 此时我们有一个消费者就在“蹲点”这个死信队列，消息一进入死信队列，就立马被消费了。
 */
@Configuration
public class RabbitConfig {
    public static final String JAVA_BOY_QUEUE_NAME = "javaboy_queue_name";
    public static final String JAVA_BOY_EXCHANGE_NAME = "javaboy_exchange_name";
    /**
     * 死信队列
     */
    public static final String DELAY_EXCHANGE_NAME = "delay_exchange_name";
    public static final String DELAY_QUEUE_NAME = "delay_queue_name";

    @Bean
    Binding msgBinding() {
        return BindingBuilder.bind(msgQueue())
                .to(msgExchange())
                .with(JAVA_BOY_QUEUE_NAME);
    }

    @Bean
    DirectExchange msgExchange() {
        return new DirectExchange(JAVA_BOY_EXCHANGE_NAME, true, false);
    }

    @Bean
    Queue msgQueue() {
        Map<String, Object> args = new HashMap<>();
        // 消息过期时间，毫秒
        args.put("x-message-ttl", 10000);
        // 设置死信交换机
        args.put("x-dead-letter-exchange", DELAY_EXCHANGE_NAME);
        // 设置死信 routing-key
        args.put("x-dead-letter-routing-key", DELAY_QUEUE_NAME);
        return new Queue(JAVA_BOY_QUEUE_NAME, true, false, false, args);
    }

    /**
     * 绑定死信队列和死信交换机
     *
     * @return
     */
    @Bean
    Binding dlxBinding() {
        return BindingBuilder.bind(dlxQueue())
                .to(dlxExchange())
                .with(DELAY_QUEUE_NAME);
    }

    /**
     * 死信交换机
     */
    @Bean
    DirectExchange dlxExchange() {
        return new DirectExchange(DELAY_EXCHANGE_NAME, true, false);
    }

    /**
     * 死信队列
     */
    @Bean
    Queue dlxQueue() {
        return new Queue(DELAY_QUEUE_NAME, true, false, false);
    }
}
