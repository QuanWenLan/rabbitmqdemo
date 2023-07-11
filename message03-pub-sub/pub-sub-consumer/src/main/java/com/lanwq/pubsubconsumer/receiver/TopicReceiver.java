package com.lanwq.pubsubconsumer.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @program: rabbitmqdemo->TopicReceiver
 * @description: topic 消费者
 * @author: lanwenquan
 * @date: 2022-06-02 21:22
 */
@Component
public class TopicReceiver {

    @RabbitListener(queues = "phone")
    public void handler1(String message) {
        System.out.println("PhoneReceiver:" + message);
    }

    @RabbitListener(queues = "xiaomi")
    public void handler2(String message) {
        System.out.println("XiaoMiReceiver:" + message);
    }

    @RabbitListener(queues = "huawei")
    public void handler3(String message) {
        System.out.println("HuaWeiReceiver:" + message);
    }
}
