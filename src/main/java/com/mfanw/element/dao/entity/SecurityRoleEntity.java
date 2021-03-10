package com.mfanw.element.dao.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mengwei
 */
@Getter
@Setter
@ToString
public class SecurityRoleEntity implements Serializable {

    private static final long serialVersionUID = -5648323189812934716L;

    @ApiModelProperty(value = "角色ID")
    private Long id;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色描述")
    private String description;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "启用状态：0->禁用；1->启用")
    private Integer status;
}
