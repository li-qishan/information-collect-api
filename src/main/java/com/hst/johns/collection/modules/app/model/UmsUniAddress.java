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
@TableName("ums_uni_address")
@ApiModel(value="UmsUniAddress对象", description="")
public class UmsUniAddress implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "自增id")
    private Integer id;

    @ApiModelProperty(value = "uid")
    private Integer uid;

    @ApiModelProperty(value = "虚拟地址ID 全局唯一")
    private String uniAddresId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "证件类型")
    private Integer certificateType;

    @ApiModelProperty(value = "证件名称")
    private String certificateName;

    @ApiModelProperty(value = "证件号码")
    private String certificateNum;

    @ApiModelProperty(value = "省份编码")
    private String provinceCode;

    @ApiModelProperty(value = "省份名称")
    private String provinceName;

    @ApiModelProperty(value = "城市编码")
    private String cityCode;

    @ApiModelProperty(value = "城市名称")
    private String cityName;

    @ApiModelProperty(value = "区域编码")
    private String countyCode;

    @ApiModelProperty(value = "区域名称")
    private String countyName;

    @ApiModelProperty(value = "街道编码")
    private String townCode;

    @ApiModelProperty(value = "街道名称")
    private String townName;

    @ApiModelProperty(value = "公开地址")
    private String publicAddress;

    @ApiModelProperty(value = "私密地址")
    private String privateAddress;

    @ApiModelProperty(value = "快递编码")
    private Integer expressType;

    @ApiModelProperty(value = "快递名称")
    private String expressName;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "状态 0有效 1失效 ")
    private String status;

    @ApiModelProperty(value = "是否删除 0 未删除 1 已删除")
    private String isDel;


}
