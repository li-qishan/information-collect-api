package com.hst.johns.collection.modules.app.controller;


import com.hst.johns.collection.security.util.Constants;
import com.hst.johns.collection.security.util.HttpClientUtil;
import com.hst.johns.collection.security.util.JsonUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

/**
 * 微信登录相关模块
 */
@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("weChat")
public class WeChatController {

    private  final WxMpService wxMpService;


    @GetMapping(value = "auth")
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
//        String url = "/weChat/getOpenId";
        log.info("【yuanc-重定向接收的页面】 获取 returnUrl，returnUrl = {}", returnUrl);
        //本机
        String url = "http://localhost:7777/wechat/getOpenId";
        // 获取微信返回的重定向url
        String redirectUrl =  String.format(Constants.OAUTH2_BUILD_AUTHORIZATION_URL, wxMpService.getWxMpConfigStorage().getAppId(),
                HttpClientUtil.encodeURIComponent(url),
                WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
        log.info("【微信网页授权】获取code，redirectUrl = {}", redirectUrl);
        return "redirect:" + redirectUrl;
    }

    @GetMapping(value = "getOpenId")
    public String getOpenId(@RequestParam("code") String code,
                            @RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        String openId = "";
        String unionId = "";
        try {
            // 使用code换取access_token信息
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            //验证access token
            boolean valid = wxMpService.oauth2validateAccessToken(wxMpOAuth2AccessToken);
            log.info("【微信网页授权】验证access token，accessToken = {}", valid);
            if(valid){
                WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
                log.info("【微信网页授权】获取openId，openId = {}", JsonUtil.toJSONString(wxMpUser));
                openId = wxMpUser.getOpenId();
                unionId = wxMpUser.getUnionId();
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
            log.error("【微信网页授权】异常，{}", e);
        }
        // 重定向跳转的页面
        if(returnUrl.contains("?")){
            returnUrl = returnUrl + "&openId=" + openId +"&unionId="+unionId;
        }else{
            returnUrl = returnUrl + "?openId=" + openId +"&unionId="+unionId;
        }
        log.info("【yuanc-重定向跳转的页面】 获取 returnUrl，returnUrl = {}", returnUrl);
        return "redirect:" + returnUrl;
    }




}
