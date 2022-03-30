package com.lxt.rabbitmqcluster.controller;

import com.lxt.rabbitmqcluster.vo.UserOrderVO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("mq")
public class MqTestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("msg")
    public boolean msg(){
        UserOrderVO userOrderVO = new UserOrderVO();
        userOrderVO.setOrderNo(UUID.randomUUID().toString());
        userOrderVO.setUserId(1L);
        userOrderVO.setNumbers(10);
        userOrderVO.setProductId(2L);
        // mq发送消息，进入延迟队列
        rabbitTemplate.convertAndSend("order-event-exchange","order.create.order",userOrderVO);
        return  true;
    }


}
