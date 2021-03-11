package com.mfanw.element.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.mfanw.element.configuration.jwt.JwtUserDetailsServiceImpl;
import com.mfanw.element.dao.convertor.Entity2FormConvertor;
import com.mfanw.element.dao.entity.SecurityRoleEntity;
import com.mfanw.element.dao.entity.SecurityUserEntity;
import com.mfanw.element.dao.mapper.SecurityRoleMapper;
import com.mfanw.element.dao.mapper.SecurityUserMapper;
import com.mfanw.element.enums.EnumErrorResult;
import com.mfanw.element.util.JsonResult;
import com.mfanw.element.util.JwtTokenUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户Controller
 *
 * @author mengwei
 */
@Controller
@RequestMapping("/vue-element-admin/user")
public class SecurityUserController {

    @Autowired
    private JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private SecurityRoleMapper securityRoleMapper;

    @Autowired
    private SecurityUserMapper securityUserMapper;

    @PostMapping("/login")
    @ResponseBody
    public JsonResult login(@RequestBody JSONObject jsonParam) {
        String username = MapUtils.getString(jsonParam, "username", "admin");
        String password = MapUtils.getString(jsonParam, "password", "admin");
        final UserDetails userDetails = jwtUserDetailsServiceImpl.loadUserByUsername(username);
        if (username.length() != password.length()) {
            return JsonResult.fail(EnumErrorResult.LOGIN_PASSWORD_ERROR);
        }
        Map<String, String> data = Maps.newHashMap();
        data.put("token", jwtTokenUtil.generateToken(userDetails));
        return JsonResult.success(data);
    }

    @PostMapping("/logout")
    @ResponseBody
    public JsonResult logout(@RequestBody(required = false) JSONObject jsonParam) {
        return JsonResult.success("success");
    }

    @GetMapping("/info")
    @ResponseBody
    public JsonResult info(String token) {
        final String username = jwtTokenUtil.getUsernameFromToken(token);
        if (StringUtils.isBlank(username)) {
            return JsonResult.fail(EnumErrorResult.USERNAME_NOT_FOUND);
        }
        final SecurityUserEntity securityUserEntity = securityUserMapper.getByUsername(username);
        if (StringUtils.isBlank(username)) {
            return JsonResult.fail(EnumErrorResult.USERNAME_NOT_FOUND);
        }
        final List<SecurityRoleEntity> securityRoleEntities = securityRoleMapper.getListByUserId(securityUserEntity.getId());
        if (CollectionUtils.isEmpty(securityRoleEntities)) {
            return JsonResult.fail(EnumErrorResult.USER_ROLE_MISSING);
        }
        return JsonResult.success(Entity2FormConvertor.buildSecurityUserForm(securityUserEntity, securityRoleEntities));
    }


}
