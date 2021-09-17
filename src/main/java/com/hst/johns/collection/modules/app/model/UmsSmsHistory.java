package com.hst.johns.collection.modules.app.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author jhons-li
 * @since 2021-08-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_sms_history")
@ApiModel(value="UmsSmsHistory对象", description="")
public class UmsSmsHistory implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "mid")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "内容")
    private String concent;

    @ApiModelProperty(value = "是否发送成功")
    private String isSuccess;

    @ApiModelProperty(value = "发送时间")
    private Date releaseTime;


}
