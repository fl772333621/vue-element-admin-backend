package com.mfanw.element.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.mfanw.element.dao.convertor.Entity2FormConvertor;
import com.mfanw.element.dao.entity.SecurityRoleEntity;
import com.mfanw.element.dao.entity.SecurityRouteEntity;
import com.mfanw.element.dao.mapper.SecurityRoleMapper;
import com.mfanw.element.dao.mapper.SecurityRouteMapper;
import com.mfanw.element.enums.EnumErrorResult;
import com.mfanw.element.form.SecurityRoleForm;
import com.mfanw.element.util.JsonResult;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户Controller
 *
 * @author mengwei
 */
@Controller
@RequestMapping("/vue-element-admin")
public class SecurityRoleController {

    @Autowired
    private SecurityRoleMapper securityRoleMapper;

    @Autowired
    private SecurityRouteMapper securityRouteMapper;

    @GetMapping("/roles")
    @ResponseBody
    public JsonResult roles(@RequestBody(required = false) JSONObject jsonParam) {
        // role
        final List<SecurityRoleEntity> securityRoleEntities = securityRoleMapper.getList();
        if (CollectionUtils.isEmpty(securityRoleEntities)) {
            return JsonResult.fail(EnumErrorResult.USER_ROLE_MISSING);
        }
        // route
        List<SecurityRouteEntity> securityRouteEntities = securityRouteMapper.getList();
        // form
        List<SecurityRoleForm> securityRoleForms = Lists.newArrayList();
        for (SecurityRoleEntity securityRoleEntity : securityRoleEntities) {
            securityRoleForms.add(Entity2FormConvertor.buildSecurityRoleForm(securityRoleEntity, securityRouteEntities));
        }
        return JsonResult.success(securityRoleForms);
    }

    @PostMapping("/role")
    @ResponseBody
    public JsonResult roleInsert(@RequestBody(required = false) JSONObject jsonParam) {
        // role
        SecurityRoleEntity securityRoleEntity = new SecurityRoleEntity();
        securityRoleEntity.setName(MapUtils.getString(jsonParam, "name", "name"));
        securityRoleEntity.setDescription(MapUtils.getString(jsonParam, "description", "description"));
        securityRoleMapper.insert(securityRoleEntity);
        return JsonResult.success(null);
    }

    @DeleteMapping("/role/{key}")
    @ResponseBody
    public JsonResult roleDelete(@PathVariable(value = "key", required = false) Long key) {
        // role
        securityRoleMapper.delete(key);
        return JsonResult.success(null);
    }
}
