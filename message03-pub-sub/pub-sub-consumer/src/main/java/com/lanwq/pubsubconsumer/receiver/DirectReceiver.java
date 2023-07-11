package com.lanwq.pubsubconsumer.receiver;


import com.lanwq.pubsubconsumer.config.DirectExchangeConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @program: rabbitmqdemo->DirectReceiver
 * @description:
 * @author: lanwenquan
 * @date: 2022-06-02 18:05
 */
@Component
public class DirectReceiver {
    @RabbitListener(queues = DirectExchangeConfig.DIRECT_QUEUE_NAME)
    public void handler1(String msg) {
        System.out.println("DirectReceiver:" + msg);
    }
}
