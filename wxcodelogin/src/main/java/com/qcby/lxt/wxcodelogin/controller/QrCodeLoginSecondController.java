package com.qcby.lxt.wxcodelogin.controller;


import com.alibaba.fastjson.JSONObject;
import com.qcby.lxt.wxcodelogin.properties.WxConfig;
import com.qcby.lxt.wxcodelogin.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


/**
 * @ClassName QrCodeLoginSecondController
 * @Description 微信扫码登录
 * @Author lxt
 * @Date 2021/7/1 23:26
 */
@Slf4j
@Controller
@RequestMapping("qrCodeSecondLogin")
public class QrCodeLoginSecondController {

    @Autowired
    private WxConfig wxConfig;
    @Autowired
    private HttpSession httpSession;

    /**
     *  获取微信用户信息
     * @param code
     * @return
     */
    @RequestMapping("getWxUserInfo")
    public String getWxUserInfo(String code){
        String getOpenIdUrl = wxConfig.getOpenIdUrl().replace("APPID", wxConfig.getAppId()).replace("APPSECRET", wxConfig.getAppSecret()).replace("CODE", code);
        String response = HttpClientUtil.doGet(getOpenIdUrl);
        JSONObject jsonObject = JSONObject.parseObject(response);
        String accessToken = jsonObject.get("access_token") == null ? null :jsonObject.get("access_token").toString();
        // 获取openId
        String openId = jsonObject.get("openid") == null ? null : jsonObject.get("openid").toString();
        log.info("微信用户信息openId:{}",openId);
        /**
         *  1、第一次扫码，进行账号绑定，用户和openId关联，选择性获取微信用户信息
         *  2、以后根据openid获取用户信息
         */
        // 选择性获取微信用户信息
        JSONObject userJson  = getUserInfo(openId,accessToken);
        log.info("微信用户信息userJson:{}",userJson);
        return "redirect:/";
    }


    /**
     *  获取微信用户信息
     * @param openId
     * @param accessToken
     * @return
     */
    private JSONObject getUserInfo(String openId,String accessToken){
        String userInfoUrl = wxConfig.getUserInfoUrl().replace("ACCESS_TOKEN",accessToken).replace("OPENID", openId);
        String userInfo = HttpClientUtil.doGet(userInfoUrl);
        JSONObject jsonObject = JSONObject.parseObject(userInfo);
        return jsonObject;
    }

}

