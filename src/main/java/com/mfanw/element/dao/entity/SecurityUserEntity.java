package com.mfanw.element.dao.entity;

import com.mfanw.element.form.BaseForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 用户权限校验实体类（包含用户、密码、角色）
 *
 * @author mengwei
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SecurityUserEntity extends BaseForm {

    private static final long serialVersionUID = -5633052258669173203L;

    @ApiModelProperty(value = "用户ID")
    private Long id;

    @ApiModelProperty(value = "登录用户")
    private String username;

    @ApiModelProperty(value = "登录密码")
    private String password;

    @ApiModelProperty(value = "用户名称")
    private String name;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "个人简介")
    private String introduction;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date modifyTime;

    @ApiModelProperty(value = "启用状态：0->禁用；1->启用")
    private Integer status;
}