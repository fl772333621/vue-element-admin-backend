package com.mfanw.element.dao.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户权限校验实体类（包含用户、密码、角色）
 *
 * @author mengwei
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SecurityUserEntity implements Serializable {

    private static final long serialVersionUID = 8552708127179299433L;

    @ApiModelProperty(value = "用户ID")
    private Long id;

    @ApiModelProperty(value = "用户登录名称")
    private String username;

    @ApiModelProperty(value = "用户登录密码")
    private String password;

    @ApiModelProperty(value = "头像")
    private String icon;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "备注信息")
    private String note;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "最后登录时间")
    private Date loginTime;

    @ApiModelProperty(value = "帐号启用状态：0->禁用；1->启用")
    private Integer status;
}