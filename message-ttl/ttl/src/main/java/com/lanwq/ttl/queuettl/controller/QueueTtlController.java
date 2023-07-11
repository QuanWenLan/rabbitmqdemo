package com.lanwq.ttl.queuettl.controller;

import com.lanwq.ttl.queuettl.config.QueueTtlRabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨  dlx
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 *
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@RestController
@RequestMapping("/queue")
public class QueueTtlController {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public void helloQueue() {
        rabbitTemplate.convertAndSend(QueueTtlRabbitConfig.JAVABOY_QUEUES_DELAY_EXCHANGE_NAME, QueueTtlRabbitConfig.JAVABOY_QUEUES_DELAY_QUEUE_NAME, "hello 江南一点雨");
    }
}
