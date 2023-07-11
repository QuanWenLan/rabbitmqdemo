package com.lanwq.dlxdemo.controller;

import com.lanwq.dlxdemo.config.DLXConfig;
import com.lanwq.dlxdemo.config.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vin lan
 * @className HelloController
 * @description
 * @createTime 2022-10-17  17:02
 **/
@RestController
public class HelloController {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/hello")
    public void hello() {
        Message message = MessageBuilder.withBody("hello javaboy".getBytes())
//                设置一条消息的过期时间
//                .setExpiration("10000")
                .build();
        rabbitTemplate.convertAndSend(RabbitConfig.MSG_EXCHANGE_NAME, RabbitConfig.MSG_QUEUE_NAME, message);
    }
}
