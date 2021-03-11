package com.mfanw.element.dao.entity;

import com.mfanw.element.form.BaseForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author mengwei
 */
@Getter
@Setter
@ToString
public class SecurityRoleRouteEntity extends BaseForm {

    private static final long serialVersionUID = 7826800453103806832L;

    @ApiModelProperty(value = "角色ID")
    private String roleId;

    @ApiModelProperty(value = "页面ID")
    private Long routeId;

}
