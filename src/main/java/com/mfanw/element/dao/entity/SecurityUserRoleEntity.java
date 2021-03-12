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
public class SecurityUserRoleEntity extends BaseForm {

    private static final long serialVersionUID = -6982271411796329747L;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "角色ID")
    private String roleKey;

}
