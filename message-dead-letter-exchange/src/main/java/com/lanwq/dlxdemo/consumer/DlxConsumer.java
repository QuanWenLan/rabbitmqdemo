package com.lanwq.dlxdemo.consumer;

import com.lanwq.dlxdemo.config.DLXConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Vin lan
 * @className HandlerMsg
 * @description 死信队列的消费
 * @createTime 2022-10-18  14:02
 **/
@Component
public class DlxConsumer {
    @RabbitListener(queues = DLXConfig.DLX_QUEUE_NAME)
    public void dlxHandle(String msg) {
        System.out.println("dlx msg = " + msg + "------->" + Thread.currentThread().getName());
        // dlx msg = hello javaboy------->org.springframework.amqp.rabbit.RabbitListenerEndpointContainer#0-1
    }
}
