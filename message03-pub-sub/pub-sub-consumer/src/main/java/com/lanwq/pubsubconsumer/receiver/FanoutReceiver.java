package com.lanwq.pubsubconsumer.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @program: rabbitmqdemo->FanoutReceiver
 * @description: fanout 消息消费者
 * @author: lanwenquan
 * @date: 2022-06-02 18:15
 */
@Component
public class FanoutReceiver {

    @RabbitListener(queues = "queue-one")
    public void handler1(String message) {
        System.out.println("FanoutReceiver:handler1:" + message);
    }

    @RabbitListener(queues = "queue-two")
    public void handler2(String message) {
        System.out.println("FanoutReceiver:handler2:" + message);
    }
}
