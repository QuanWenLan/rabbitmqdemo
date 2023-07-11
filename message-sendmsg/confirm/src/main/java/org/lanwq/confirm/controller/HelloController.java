package org.lanwq.confirm.controller;

import org.lanwq.confirm.config.RabbitConfig;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@RestController
public class HelloController {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public void hello() {
        // 1 发送消息到一个不存在的交换机中，存在的队列，（不会路由到队列）
        // 输出：bc7ef958-231c-4313-b772-be2c588a6c9b 消息未到达交换机，
        rabbitTemplate.convertAndSend("RabbitConfig.JAVABOY_EXCHANGE_NAME", RabbitConfig.JAVABOY_QUEUE_NAME, "hello javaboy!", new CorrelationData(UUID.randomUUID().toString()));
        // 2 发送消息到存在的交换机，不存在的队列（会到达交换机，不会路由到队列）
        // 输出： null 消息未成功到达队列；
        // 67a8df97-1eed-4eb0-ab5b-ef7fac35b53e 消息成功到达交换机
        rabbitTemplate.convertAndSend(RabbitConfig.JAVABOY_EXCHANGE_NAME, "RabbitConfig.JAVABOY_QUEUE_NAME", "hello javaboy!", new CorrelationData(UUID.randomUUID().toString()));

        // 3 发送消息到存在的交换机和队列中
        rabbitTemplate.convertAndSend(RabbitConfig.JAVABOY_EXCHANGE_NAME, RabbitConfig.JAVABOY_QUEUE_NAME, "hello javaboy!", new CorrelationData(UUID.randomUUID().toString()));
    }
}
