// +----------------------------------------------------------------------------
// | 闪速码java短信sdk
// +----------------------------------------------------------------------------
// | Copyright (c) 2014-2021 https://www.shansuma.com
// +----------------------------------------------------------------------------
// | 闪速码短信平台支持网页在线发送、API集成发送、私有化部署三种形式，可免费试用200条
// +----------------------------------------------------------------------------
// | Author: 橘子俊，开发文档请访问 http://sms.shansuma.com/docs
// +----------------------------------------------------------------------------

package com.hst.johns.collection.modules.app.client;

import com.hst.johns.collection.modules.app.client.Client;

public class TestClient {

    public static void main(String[] args) {

        Client client = new Client();
        client.setAppId("你的开发者id");     //开发者ID，在【设置】-【开发设置】中获取
        client.setSecretKey("你的开发者密钥");    //开发者密钥，在【设置】-【开发设置】中获取
        client.setVersion("1.0");

        /**
         *   json格式可在 bejson.com 进行校验
         */
        Client.Request request = new Client.Request();
        request.setBizContent("{\n" +
                "    \"mobile\": [\n" +
                "        \"13800138000\"\n" +
                "    ],\n" +
                "    \"type\": 0,\n" +
                "    \"template_id\": \"ST_2020101100000005\",\n" +
                "    \"sign\": \"短信签名\",\n" +
                "    \"send_time\": \"\",\n" +
                "    \"params\": {\n" +
                "        \"code\": 1234\n" +
                "    }\n" +
                "}");  // 这里是json字符串，send_time 为空时可以为null, params 为空时可以为null,短信签名填写审核后的签名本身，不需要填写签名id
        request.setMethod("sms.message.send");
        System.out.println( client.execute(request) );
    }

}
