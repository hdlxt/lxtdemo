package com.lxt.rabbitmqcluster.listener;


import com.lxt.rabbitmqcluster.vo.UserOrderVO;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 定时关闭订单
 *
 */
@Slf4j
@RabbitListener(queues = "order.release.order.queue")
@Service
public class OrderCloseListener {


    @RabbitHandler
    public void listener(UserOrderVO userOrderVo, Channel channel, Message message) throws IOException {
        log.info("收到过期的订单信息，准备关闭订单:userOrderVo{}",userOrderVo);
        try {
            // 关闭订单
            // 消息确认消费
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (Exception e) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
        }

    }

}
