package com.mfanw.element.dao.convertor;

import com.alibaba.fastjson.JSONObject;
import com.mfanw.element.dao.entity.SecurityRoleEntity;
import org.apache.commons.collections.MapUtils;

public class Json2EntityConvertor {

    public static SecurityRoleEntity buildSecurityRoleEntity(JSONObject jsonParam) {
        SecurityRoleEntity securityRoleEntity = new SecurityRoleEntity();
        securityRoleEntity.setKey(MapUtils.getLong(jsonParam, "key"));
        securityRoleEntity.setName(MapUtils.getString(jsonParam, "name"));
        securityRoleEntity.setDescription(MapUtils.getString(jsonParam, "description"));
        securityRoleEntity.setStatus(MapUtils.getInteger(jsonParam, "status"));
        return securityRoleEntity;
    }
}
