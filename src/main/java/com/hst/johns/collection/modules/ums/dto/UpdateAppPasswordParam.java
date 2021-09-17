package com.hst.johns.collection.modules.ums.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * 修改用户名密码参数
 * Created by macro on 2019/10/9.
 */
@Getter
@Setter
public class UpdateAppPasswordParam {
    @NotEmpty
    @ApiModelProperty(value = "手机号", required = true)
    private String phone;
//    @NotEmpty
//    @ApiModelProperty(value = "旧密码", required = true)
//    private String oldPassword;
    @NotEmpty
    @ApiModelProperty(value = "新密码", required = true)
    private String newPassword;
}
