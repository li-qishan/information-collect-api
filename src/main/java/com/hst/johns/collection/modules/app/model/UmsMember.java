package com.hst.johns.collection.modules.app.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author jhons-li
 * @since 2021-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_member")
@ApiModel(value="UmsMember对象", description="")
public class UmsMember implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "自增ID")
    private Integer id;

    @ApiModelProperty(value = "关联系统用户表ID")
    private Integer uid;

    @ApiModelProperty(value = "会员姓名")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "身份类别[1个人,2自提点]")
    private String type;

    @ApiModelProperty(value = "提货地址")
    private String postion;

    @ApiModelProperty(value = "经纬度信息json字符串")
    private String jsonStr;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "用户状态 0 未审核 1 已通过 个人 默认 已通过 1 自提点 默认 未审核 0  需要手动审核未已通过")
    private String status;

    @ApiModelProperty(value = "是否删除")
    private String isDel;


}
