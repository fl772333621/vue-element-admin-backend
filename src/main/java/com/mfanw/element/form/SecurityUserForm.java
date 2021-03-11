package com.mfanw.element.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class SecurityUserForm extends BaseForm {

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

    @ApiModelProperty(value = "用户角色")
    private List<String> roles;
}
