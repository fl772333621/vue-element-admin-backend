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
public class SecurityRoleEntity extends BaseForm {

    private static final long serialVersionUID = 5188936337858084402L;

    @ApiModelProperty(value = "角色ID")
    private Long key;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色描述")
    private String description;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date modifyTime;

    @ApiModelProperty(value = "启用状态：0->禁用；1->启用")
    private Integer status;

}
