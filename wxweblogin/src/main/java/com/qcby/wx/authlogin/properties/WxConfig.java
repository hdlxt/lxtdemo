package com.qcby.wx.authlogin.properties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName WxConfig
 * @Description TODO
 * @Author lxt
 * @Date 2021/7/16 11:18
 */
@Component
@ConfigurationProperties(prefix = "wx")
@Data
public class WxConfig {
    private String appId;
    private String appSecret;
    private String server;
}
