package com.hst.johns.collection.modules.app.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * 地区表
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "AreaPageQuery", description = "地区表")
public class AreaPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "id")
    private Long id;
    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;
    /**
     * 全名
     */
    @ApiModelProperty(value = "全名")
    private String fullName;
    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private String longitude;
    /**
     * 维度
     */
    @ApiModelProperty(value = "维度")
    private String latitude;

    /**
     * 行政区级
     */
    @ApiModelProperty(value = "行政区级")
    private String level;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    protected String label;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private int state;

    /**
     * 状态
     */
    @ApiModelProperty(value = "排序")
    private int sortValue;


}
