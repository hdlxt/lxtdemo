package com.qcby.wx.authlogin.controller;

import com.qcby.wx.authlogin.properties.WxConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author lxt
 * @Date 2021/7/16 11:08
 */
@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private WxConfig wxConfig;

    @RequestMapping
    public String index(Model model) throws UnsupportedEncodingException {
        model.addAttribute("wxConfig",wxConfig);
        // 进行编码
        model.addAttribute("redirectUri", URLEncoder.encode(wxConfig.getServer()+"/webAuthLogin/getCode","UTF-8"));
        return "login";
    }


}
