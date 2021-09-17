package com.hst.johns.collection.modules.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UmsUniAddressParam {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "uid")
    private Integer uid;

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
}
