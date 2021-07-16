package com.qcby.wx.authlogin.controller;


import com.alibaba.fastjson.JSONObject;
import com.qcby.wx.authlogin.properties.WxConfig;
import com.qcby.wx.authlogin.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @ClassName WebAuthLoginController
 * @Description TODO
 * @Author lxt
 * @Date 2021/7/16 11:06
 */
@Controller
@RequestMapping("webAuthLogin")
public class WebAuthLoginController {
    //获取openid和access_token的连接
    private static String getOpenId = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=APPSECRET&code=CODE&grant_type=authorization_code";
    //获取用户基本信息的连接
    private static String getUserInfo = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    @Autowired
    private WxConfig wxConfig;
    @Autowired
    private HttpSession httpSession;
    /**
     *  微信网页授权回调
     * @param code
     * @return
     */
    @RequestMapping("getCode")
    public String getCode(String code){
        String getOpenIdUrl = getOpenId.replace("APPID", wxConfig.getAppId()).replace("APPSECRET", wxConfig.getAppSecret()).replace("CODE", code);
        String response = HttpClientUtil.doGet(getOpenIdUrl);
        JSONObject jsonObject = JSONObject.parseObject(response);
        String accessToken = jsonObject.get("access_token") == null ? null :jsonObject.get("access_token").toString();
        String openId = jsonObject.get("openid") == null ? null : jsonObject.get("openid").toString();
        httpSession.setAttribute("accessToken",accessToken);
        httpSession.setAttribute("openId",openId);
        httpSession.setAttribute("userInfo",getUserInfo(openId,accessToken));
        return "redirect:/";
    }
    /**
     *  获取微信用户信息
     * @param openId
     * @param accessToken
     * @return
     */
    private JSONObject getUserInfo(String openId,String accessToken){
        String userInfoUrl = getUserInfo.replace("ACCESS_TOKEN",accessToken).replace("OPENID", openId);
        String userInfo = HttpClientUtil.doGet(userInfoUrl);
        JSONObject jsonObject = JSONObject.parseObject(userInfo);
        return jsonObject;
    }
}
