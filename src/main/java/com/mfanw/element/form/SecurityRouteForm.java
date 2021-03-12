package com.mfanw.element.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 权限专用页面Form
 *
 * @author mengwei
 */
@Getter
@Setter
@ToString
public class SecurityRouteForm extends BaseForm {

    @ApiModelProperty(value = "页面ID")
    private Long id;

    @ApiModelProperty(value = "页面路径")
    private String path;

    @ApiModelProperty(value = "页面名称")
    private String name;

    @ApiModelProperty(value = "页面组件")
    private String component;

    @ApiModelProperty(value = "页面meta")
    private SecurityRouteMetaForm meta;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date modifyTime;

    @ApiModelProperty(value = "启用状态：0->禁用；1->启用")
    private Integer status;

}
