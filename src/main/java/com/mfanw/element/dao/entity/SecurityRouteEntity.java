package com.mfanw.element.dao.entity;

import com.mfanw.element.form.BaseForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author mengwei
 */
@Getter
@Setter
@ToString
public class SecurityRouteEntity extends BaseForm {

    private static final long serialVersionUID = -6464115463675474890L;

    @ApiModelProperty(value = "页面ID")
    private Long id;

    @ApiModelProperty(value = "父页面ID")
    private Long parentId;

    @ApiModelProperty(value = "页面路径")
    private String path;

    @ApiModelProperty(value = "页面名称")
    private String name;

    @ApiModelProperty(value = "页面组件")
    private String component;

    @ApiModelProperty(value = "页面meta标题")
    private String title;

    @ApiModelProperty(value = "页面meta图标")
    private String icon;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date modifyTime;

    @ApiModelProperty(value = "启用状态：0->禁用；1->启用")
    private Integer status;

    @ApiModelProperty(value = "角色key集合-加工字段")
    private String roles;
}
