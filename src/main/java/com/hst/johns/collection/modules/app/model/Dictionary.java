package com.hst.johns.collection.modules.app.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.util.Date;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * 字典项
 * </p>

 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(of = {"type", "code"})
@Accessors(chain = true)
@TableName("ums_dictionary")
@ApiModel(value = "Dictionary", description = "字典项")
@AllArgsConstructor
public class Dictionary  {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    private Long id;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    @NotEmpty(message = "类型不能为空")
    @Size(max = 255, message = "类型长度不能超过255")
    @TableField(value = "type", condition = LIKE)
    private String type;

    /**
     * 类型标签
     */
    @ApiModelProperty(value = "类型标签")
    @NotEmpty(message = "类型标签不能为空")
    @Size(max = 255, message = "类型标签长度不能超过255")
    @TableField(value = "label", condition = LIKE)
    private String label;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    @NotEmpty(message = "编码不能为空")
    @Size(max = 64, message = "编码长度不能超过64")
    @TableField(value = "code", condition = LIKE)
    private String code;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @NotEmpty(message = "名称不能为空")
    @Size(max = 64, message = "名称长度不能超过64")
    @TableField(value = "name", condition = LIKE)
    private String name;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField("state")
    private Boolean state;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Size(max = 255, message = "描述长度不能超过255")
    @TableField(value = "describe_", condition = LIKE)
    private String describe;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    @TableField("sort_value")
    private Integer sortValue;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    @Size(max = 255, message = "图标长度不能超过255")
    @TableField(value = "icon", condition = LIKE)
    private String icon;

    /**
     * css样式
     */
    @ApiModelProperty(value = "css样式")
    @Size(max = 255, message = "css样式长度不能超过255")
    @TableField(value = "css_style", condition = LIKE)
    private String cssStyle;

    /**
     * 类选择器
     */
    @ApiModelProperty(value = "类选择器")
    @Size(max = 255, message = "类选择器长度不能超过255")
    @TableField(value = "css_class", condition = LIKE)
    private String cssClass;

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

}
