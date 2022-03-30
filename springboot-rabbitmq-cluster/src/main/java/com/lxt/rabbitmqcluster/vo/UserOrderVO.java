package com.lxt.rabbitmqcluster.vo;

import lombok.Data;

@Data
public class UserOrderVO {
    private Long userId;
    private Long productId;
    private Integer numbers;
    private String orderNo;

}
