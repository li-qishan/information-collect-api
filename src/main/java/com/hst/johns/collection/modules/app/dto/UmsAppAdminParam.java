package com.hst.johns.collection.modules.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * 用户登录参数
 * Created by macro on 2018/4/26.
 */
@Getter
@Setter
public class UmsAppAdminParam {

    @ApiModelProperty(value = "用户名", required = true)
    private String username;
    @NotEmpty
    @ApiModelProperty(value = "密码", required = true)
    private String password;
    @ApiModelProperty(value = "用户头像")
    private String icon;
    @Email
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "用户昵称")
    private String nickName;
    @ApiModelProperty(value = "备注")
    private String note;
    @NotEmpty
    @ApiModelProperty(value = "手机号")
    private String phone;
    @NotEmpty
    @ApiModelProperty(value = "身份类别[1 个人,2 自提点]")
    private String type;
    @ApiModelProperty(value = "提货地点")
    private String postion;
    @ApiModelProperty(value = "经纬度信息json字符串")
    private String jsonStr;
}
