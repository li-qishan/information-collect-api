package com.hst.johns.collection.security.util;

public class Constants {

    public static String OAUTH2_BUILD_AUTHORIZATION_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s&connect_redirect=1#wechat_redirect";
    public static String OAUTH2_VALIDATE_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/auth?access_token=%s&openid=%s";
    public static String OAUTH2_REFRESH_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s";


}
