package com.hst.johns.collection.modules.app.service.impl;


import com.hst.johns.collection.common.api.CommonResult;
import com.hst.johns.collection.common.service.RedisService;
import com.hst.johns.collection.modules.app.service.AppMemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Random;

/**
 * 会员管理Service实现类
 * Created by macro on 2018/8/3.
 */
@Service
@Slf4j
public class AppMemberServiceImpl implements AppMemberService {
    @Autowired
    private RedisService redisService;

    @Value("${redis.key.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;

    @Value("${redis.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public CommonResult generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        try {
            Random random = new Random();
            for (int i = 0; i < 6; i++) {
                sb.append(random.nextInt(10));
            }
            //验证码绑定手机号并存储到redis
            redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + telephone, sb.toString());
            redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone, AUTH_CODE_EXPIRE_SECONDS);
            HttpClient client = new HttpClient();
            PostMethod post = new PostMethod("http://utf8.api.smschinese.cn/");
            post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");//在头文件中设置转码
            NameValuePair[] data = {
                    new NameValuePair("Uid", "lidongdong1308244316"),
                    new NameValuePair("Key", "d41d8cd98f00b204e980"),
                    new NameValuePair("smsMob", telephone),
                    new NameValuePair("smsText", "验证码:"+sb+"，您正在注册成为新用户，感谢您的支持！")
            };
            post.setRequestBody(data);
            client.executeMethod(post);
            //打印返回消息状态
            Header[] headers = post.getResponseHeaders();
            int statusCode = post.getStatusCode();
            log.info("statusCode:" , statusCode);
            for (Header h : headers) {
                log.info(h.toString());
            }
            String result = new String(post.getResponseBodyAsString().getBytes("utf-8"));
            log.info(result);
            post.releaseConnection();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return CommonResult.success(sb.toString(), "获取验证码成功");
    }


    /**
     * 对输入的验证码进行校验
     * @param telephone
     * @param authCode
     * @return
     */
    @Override
    public CommonResult verifyAuthCode(String telephone, String authCode) {
        if (StringUtils.isEmpty(authCode)) {
            return CommonResult.failed("请输入验证码");
        }
        String realAuthCode = (String) redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        boolean result = authCode.equals(realAuthCode);
        if (result) {
            return CommonResult.success(null, "验证码校验成功");
        } else {
            return CommonResult.failed("验证码不正确");
        }
    }

}
