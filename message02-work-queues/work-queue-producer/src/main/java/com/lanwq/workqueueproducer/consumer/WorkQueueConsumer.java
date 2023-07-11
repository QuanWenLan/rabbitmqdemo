package com.lanwq.workqueueproducer.consumer;

import com.lanwq.workqueueconsumer.ttlconfig.RabbitConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Vin lan
 * @className WorkQueueConsumer
 * @description
 * @createTime 2022-05-30  10:43
 **/
@Component
public class WorkQueueConsumer {
    //    @RabbitListener(queues = RabbitConfig.WORK_QUEUE_NAME)
    public void receive(String msg) {
        System.out.println("receive = " + msg);
    }

    /**
     * concurrency 并发能力为10
     *
     * @param msg
     */
//    @RabbitListener(queues = RabbitConfig.WORK_QUEUE_NAME, concurrency = "10")
    public void receive2(String msg) {
        System.out.println("receive2 = " + msg + "------->" + Thread.currentThread().getName());
    }

    @RabbitListener(queues = RabbitConfig.WORK_QUEUE_NAME)
    public void receiveAck(Message message, Channel channel) throws IOException {
        System.out.println("receiveAck=" + message.getPayload());
        channel.basicAck(((Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG)), true);
    }

    @RabbitListener(queues = RabbitConfig.WORK_QUEUE_NAME, concurrency = "10")
    public void receive2Ack(Message message, Channel channel) throws IOException {
//        System.out.println("receive2Ack = " + message.getPayload() + "------->" + Thread.currentThread().getName());
        channel.basicReject(((Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG)), true);
    }
}
