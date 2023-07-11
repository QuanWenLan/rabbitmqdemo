package com.lanwq.helloworldproducer;

import com.lanwq.producer.config.RabbitConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProducerApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * hello world 形式的消息发送：下面这个就是直接发送到了
     * 这个时候使用的其实是默认的直连交换机（DirectExchange），DirectExchange 的路由策略是将消息队列绑定到一个 DirectExchange 上，
     * 当一条消息到达 DirectExchange 时会被转发到与该条消息 routing key 相同的 Queue 上，
     * 例如消息队列名为 “hello-queue”，则 routingkey 为 “hello-queue” 的消息会被该消息队列接收。
     */
    @Test
    void contextLoads() {
        rabbitTemplate.convertAndSend(RabbitConfig.HELLO_WORLD_QUEUE_NAME, "hello");
    }

}
