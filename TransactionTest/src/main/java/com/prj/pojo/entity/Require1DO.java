package com.prj.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 需求列表
 *
 * @author pengrj
 * @date 2023-05-21 10:35:45
 */

@ApiModel("需求列表")
@Data
@TableName("tb_require1")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Require1DO implements Serializable {
    private static final long serialVersionUID = 851245012345923421L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Integer id;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 用户采集开始时间
     */
    @ApiModelProperty(value = "用户采集开始时间")
    private Date collectionStartTime;
    /**
     * 用户采集结束时间
     */
    @ApiModelProperty(value = "用户采集结束时间")
    private Date collectionStopTime;
    /**
     * 卫星代号
     */
    @ApiModelProperty(value = "卫星代号")
    private String satellite;
    /**
     * 产品类别
     */
    @ApiModelProperty(value = "产品类别")
    private String productType;
    /**
     * 分辨率
     */
    @ApiModelProperty(value = "分辨率")
    private String resolutionType;
    /**
     * 紧急程度
     */
    @ApiModelProperty(value = "紧急程度")
    private Integer priority;
    /**
     * 标准产品生产等级
     */
    @ApiModelProperty(value = "标准产品生产等级")
    private String standardProductLevel;
    /**
     * 用户需求内容; GenJson 表达
     */
    @ApiModelProperty(value = "用户需求内容; GenJson 表达")
    private String geoJsonString;
    /**
     * 需求标识号
     */
    @ApiModelProperty(value = "需求标识号")
    private String requireTaskId;
    /**
     * 需求创建时间
     */
    @ApiModelProperty(value = "需求创建时间")
    private Date requireCreateTime;
    /**
     * 测站标识
     */
    @ApiModelProperty(value = "测站标识")
    private String antenna;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}

