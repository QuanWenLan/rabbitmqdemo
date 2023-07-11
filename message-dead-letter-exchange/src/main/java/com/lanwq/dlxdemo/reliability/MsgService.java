package com.lanwq.dlxdemo.reliability;

/**
 * @author Vin lan
 * @className MsgService
 * @description
 *  * 如何确保消息成功到达 RabbitMQ?
 *  * 1 开启事务机制
 *  * 2 发送方确认机制
 *  * 这是两种不同的方案，不可以同时开启，只能选择其中之一，如果两者同时开启，则会报错误
 * @createTime 2022-10-18  14:17
 **/
public class MsgService {
}
