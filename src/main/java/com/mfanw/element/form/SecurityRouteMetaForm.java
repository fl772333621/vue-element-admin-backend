package com.mfanw.element.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 权限专用页面元素Form
 *
 * @author mengwei
 */
@Getter
@Setter
@ToString
public class SecurityRouteMetaForm extends BaseForm {

    @ApiModelProperty(value = "页面meta标题")
    private String title;

    @ApiModelProperty(value = "页面meta图标")
    private String icon;

    @ApiModelProperty(value = "页面meta图标")
    private String roles;
}
