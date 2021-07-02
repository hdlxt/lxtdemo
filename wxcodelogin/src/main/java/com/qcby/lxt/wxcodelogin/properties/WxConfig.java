package com.qcby.lxt.wxcodelogin.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @className: WxConfig
 * @description: 读取微信配置
 * @author: lxt
 * @create: 2021-06-05 10:16
 **/
@Component
@ConfigurationProperties(prefix = "wx")
@Data
public class WxConfig {
    private String appId;
    private String appSecret;
    private String server;
    private String qrCodeUrl;
    private String tokenUrl;
    private String openIdUrl;
    private String userInfoUrl;
}
