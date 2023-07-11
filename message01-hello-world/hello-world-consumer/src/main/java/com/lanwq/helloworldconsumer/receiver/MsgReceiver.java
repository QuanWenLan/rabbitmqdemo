package com.lanwq.helloworldconsumer.receiver;

import com.lanwq.helloworldconsumer.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Vin lan
 * @className MsgReceiver
 * @description
 * @createTime 2022-05-30  10:05
 **/
@Component
public class MsgReceiver {

    /**
     * 监听 队列
     *
     * @param msg
     */
    @RabbitListener(queues = RabbitConfig.HELLO_WORLD_QUEUE_NAME)
    public void handMsg(String msg) {
        System.out.println("msg = " + msg);
    }
}
