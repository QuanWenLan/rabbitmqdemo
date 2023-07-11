package com.lanwq.pubsubproducer;

import com.lanwq.pubsubproducer.config.DirectExchangeConfig;
import com.lanwq.pubsubproducer.config.FanoutExchangeConfig;
import com.lanwq.pubsubproducer.config.RabbitHeaderConfig;
import com.lanwq.pubsubproducer.config.RabbitTopicConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PubSubProducerApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * direct 交换机
     */
    @Test
    public void directTest() {
        rabbitTemplate.convertAndSend(DirectExchangeConfig.DIRECT_QUEUE_NAME, "hello direct!");
        // 下面的不会被接收到
//        rabbitTemplate.convertAndSend(DirectExchangeConfig.DIRECT_EXCHANGE_NAME, "hello direct!");
    }

    /**
     * fanout 交换机
     */
    @Test
    public void fanoutTest() {
        rabbitTemplate.convertAndSend(FanoutExchangeConfig.FANOUT_NAME, null, "hello fanout!");
        // 发送了，两个消息处理方法都会收到消息
    }

    /**
     * topic 交换机
     */
    @Test
    public void topicTest() {
        /**
         * 第一条消息将被路由到名称为 “xiaomi” 的 Queue 上，第二条消息将被路由到名为 “huawei” 的 Queue 上，
         * 第三条消息将被路由到名为 “xiaomi” 以及名为 “phone” 的 Queue 上，
         * 第四条消息将被路由到名为 “huawei” 以及名为 “phone” 的 Queue 上，
         * 最后一条消息则将被路由到名为 “phone” 的 Queue 上
         */
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPIC_NAME, "xiaomi.news", "小米新闻..");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPIC_NAME, "huawei.news", "华为新闻..");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPIC_NAME, "xiaomi.phone", "小米手机..");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPIC_NAME, "huawei.phone", "华为手机..");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPIC_NAME, "phone.news", "手机新闻..");
    }

    /**
     * header请求头 交换机
     */
    @Test
    public void headerTest() {
        Message nameMsg = MessageBuilder
                .withBody("hello header! name-queue".getBytes())
                .setHeader("name", "sang").build();
        Message ageMsg = MessageBuilder
                .withBody("hello header! age-queue".getBytes())
                .setHeader("age", "99").build();
        rabbitTemplate.send(RabbitHeaderConfig.HEADER_NAME, null, ageMsg);
        rabbitTemplate.send(RabbitHeaderConfig.HEADER_NAME, null, nameMsg);
    }

}
