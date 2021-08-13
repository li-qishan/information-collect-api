package com.hst.johns.collection.modules.app.service;


import com.hst.johns.collection.common.api.CommonResult;

import java.io.IOException;

/**
 * 会员管理Service
 * Created by macro on 2018/8/3.
 */
public interface AppMemberService {

    /**
     * 生成验证码
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号码是否匹配
     */
    CommonResult verifyAuthCode(String telephone, String authCode);

}

