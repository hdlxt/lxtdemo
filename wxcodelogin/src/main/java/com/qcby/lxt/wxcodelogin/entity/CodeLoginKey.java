package com.qcby.lxt.wxcodelogin.entity;

import lombok.Data;

/**
 * @className: CodeLoginKey
 * @description: 用户扫码登录记录
 * @author: lxt
 * @create: 2021-06-06 11:51
 **/
@Data
public class CodeLoginKey {
    private Long id;
    private String eventKey;
    private String openId;

    public CodeLoginKey(String eventKey, String openId) {
        this.eventKey = eventKey;
        this.openId = openId;
    }
}
