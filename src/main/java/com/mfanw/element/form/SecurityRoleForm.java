package com.mfanw.element.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 权限专用角色Form
 *
 * @author mengwei
 */
@Getter
@Setter
@ToString
public class SecurityRoleForm extends BaseForm {

    @ApiModelProperty(value = "角色ID")
    private Long key;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色描述")
    private String description;

    @ApiModelProperty(value = "页面列表")
    private List<SecurityRouteForm> routes;
}
