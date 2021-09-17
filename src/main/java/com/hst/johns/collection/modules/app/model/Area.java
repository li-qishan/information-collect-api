package com.hst.johns.collection.modules.app.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


import java.util.Date;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;



@Data
@NoArgsConstructor
@TableName("c_area")
@ApiModel(value = "Area", description = "地区表")
@AllArgsConstructor
public class Area  {

    @TableField("id")
    private Long id;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    @NotEmpty(message = "编码不能为空")
    @Size(max = 64, message = "编码长度不能超过64")
    @TableField(value = "code", condition = LIKE)
    private String code;

    /**
     * 全名
     */
    @ApiModelProperty(value = "全名")
    @Size(max = 255, message = "全名长度不能超过255")
    @TableField(value = "full_name", condition = LIKE)
    private String fullName;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    @Size(max = 255, message = "经度长度不能超过255")
    @TableField(value = "longitude", condition = LIKE)
    private String longitude;

    /**
     * 维度
     */
    @ApiModelProperty(value = "维度")
    @Size(max = 255, message = "维度长度不能超过255")
    @TableField(value = "latitude", condition = LIKE)
    private String latitude;

    /**
     * 行政区级
     *
     *
     */
    @ApiModelProperty(value = "行政区级")
    @Size(max = 10, message = "行政区级长度不能超过10")
    @TableField(value = "level", condition = LIKE)
    private String level;

    /**
     * 数据来源
     */
    @ApiModelProperty(value = "数据来源")
    @Size(max = 255, message = "数据来源长度不能超过255")
    @TableField(value = "source_", condition = LIKE)
    private String source;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField("state")
    private Boolean state;

    /**
     * 内置
     */
    @ApiModelProperty(value = "内置")
    @TableField("readonly_")
    private Boolean readonly;
    /**
     * 内置
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 内置
     */
    @TableField("created_by")
    private Integer createdBy;
    /**
     * 内置
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 内置
     */
    @TableField("updated_by")
    private Integer updatedBy;

    @TableField(value = "label")
    private String label;

    @TableField("parent_id")
    private Integer parentId;

    @TableField("sort_value")
    private Integer sortValue;

}
