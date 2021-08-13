package com.hst.johns.collection.modules.app.controller;



import cn.hutool.core.lang.Validator;
import com.hst.johns.collection.common.api.CommonResult;
import com.hst.johns.collection.common.exception.ApiException;
import com.hst.johns.collection.modules.app.service.AppMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Api(tags = "AppMemberController", description = "用户登录注册管理")
@RequestMapping("/sso")
public class AppMemberController {

    @Autowired
    private AppMemberService appMemberService;

    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAuthCode(@RequestParam String telephone) {
        if (StringUtils.isEmpty(telephone)){
            throw new ApiException("手机号码为空");
        }
        if (!Validator.isMobile(telephone)){
            throw new ApiException("手机号码格式不正确");
        }
        return appMemberService.generateAuthCode(telephone);
    }

    @ApiOperation("判断验证码是否正确")
    @RequestMapping(value = "/verifyAuthCode", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePassword(@RequestParam String telephone,
                                       @RequestParam String authCode) {
        return appMemberService.verifyAuthCode(telephone,authCode);
    }
}
