package com.lanwq.ttl.queuettl.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 *  * 1 首先配置一个消息队列，new 一个 Queue：第一个参数是消息队列的名字；第二个参数表示消息是否持久化；
 *  第三个参数表示消息队列是否排他，一般我们都是设置为 false，即不排他；
 *  第四个参数表示如果该队列没有任何订阅的消费者的话，该队列会被自动删除，一般适用于临时队列。
 *  关于排他性，如果设置为 true，则该消息队列只有创建它的 Connection 才能访问，其他的 Connection 都不能访问该消息队列，
 *  如果试图在不同的连接中重新声明或者访问排他性队列，那么系统会报一个资源被锁定的错误。
 *  另一方面，对于排他性队列而言，当连接断掉的时候，该消息队列也会自动删除（无论该队列是否被声明为持久性队列都会被删除）。
 *  * 2 配置一个 DirectExchange 交换机。
 *  * 3 将交换机和队列绑定到一起。
 */
@Configuration
public class QueueTtlRabbitConfig {
    public static final String JAVABOY_QUEUES_DELAY_QUEUE_NAME = "javaboy_queues_delay_queue_name";
    public static final String JAVABOY_QUEUES_DELAY_EXCHANGE_NAME = "javaboy_queues_delay_exchange_name";

    @Bean
    Queue messageDelayQueueInQueue() {
        Map<String, Object> args = new HashMap<>();
        //给消息队列设置过期时间，该队列中的消息如果10s之内没人消费，则过期
        args.put("x-message-ttl", 10000);
        return new Queue(JAVABOY_QUEUES_DELAY_QUEUE_NAME, true, false, false, args);
    }

    @Bean
    DirectExchange messageDelayExchangeInQueue() {
        return new DirectExchange(JAVABOY_QUEUES_DELAY_EXCHANGE_NAME, true, false);
    }

    @Bean
    Binding messageDelayQueueBindingInQueue() {
        return BindingBuilder.bind(messageDelayQueueInQueue())
                .to(messageDelayExchangeInQueue())
                .with(JAVABOY_QUEUES_DELAY_QUEUE_NAME);
    }
}
