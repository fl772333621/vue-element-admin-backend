package com.mfanw.element.dao.convertor;

import com.google.common.collect.Lists;
import com.mfanw.element.dao.entity.SecurityRoleEntity;
import com.mfanw.element.dao.entity.SecurityRouteEntity;
import com.mfanw.element.dao.entity.SecurityUserEntity;
import com.mfanw.element.form.SecurityRoleForm;
import com.mfanw.element.form.SecurityRouteForm;
import com.mfanw.element.form.SecurityRouteMetaForm;
import com.mfanw.element.form.SecurityUserForm;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class Entity2FormConvertor {

    public static SecurityUserForm buildSecurityUserForm(SecurityUserEntity securityUserEntity, List<SecurityRoleEntity> securityRoleEntities) {
        SecurityUserForm securityUserForm = new SecurityUserForm();
        securityUserForm.setId(securityUserEntity.getId());
        securityUserForm.setUsername(securityUserEntity.getUsername());
        securityUserForm.setName(securityUserEntity.getName());
        securityUserForm.setAvatar(securityUserEntity.getAvatar());
        securityUserForm.setEmail(securityUserEntity.getEmail());
        securityUserForm.setIntroduction(securityUserEntity.getIntroduction());
        securityUserForm.setCreateTime(securityUserEntity.getCreateTime());
        securityUserForm.setModifyTime(securityUserEntity.getModifyTime());
        securityUserForm.setStatus(securityUserEntity.getStatus());
        securityUserForm.setRoles(securityRoleEntities.stream().map(SecurityRoleEntity::getKey).collect(Collectors.toList()));
        return securityUserForm;
    }

    public static SecurityRoleForm buildSecurityRoleForm(SecurityRoleEntity securityRoleEntity, List<SecurityRouteEntity> securityRouteEntities) {
        SecurityRoleForm securityRoleForm = new SecurityRoleForm();
        if (securityRoleEntity == null) {
            return securityRoleForm;
        }
        securityRoleForm.setKey(securityRoleEntity.getKey());
        securityRoleForm.setName(securityRoleEntity.getName());
        securityRoleForm.setDescription(securityRoleEntity.getDescription());
        securityRoleForm.setRoutes(buildSecurityRouteForms(securityRouteEntities));
        return securityRoleForm;
    }

    public static List<SecurityRouteForm> buildSecurityRouteForms(List<SecurityRouteEntity> securityRouteEntities) {
        List<SecurityRouteForm> securityRouteForms = Lists.newArrayList();
        if (CollectionUtils.isEmpty(securityRouteEntities)) {
            return securityRouteForms;
        }
        for (SecurityRouteEntity securityRouteEntity : securityRouteEntities) {
            SecurityRouteForm securityRouteForm = new SecurityRouteForm();
            securityRouteForm.setId(securityRouteEntity.getId());
            securityRouteForm.setPath(securityRouteEntity.getPath());
            securityRouteForm.setName(securityRouteEntity.getName());
            securityRouteForm.setComponent(securityRouteEntity.getComponent());
            securityRouteForm.setMeta(buildSecurityRouteMetaForm(securityRouteEntity));
            securityRouteForm.setCreateTime(securityRouteEntity.getCreateTime());
            securityRouteForm.setModifyTime(securityRouteEntity.getModifyTime());
            securityRouteForm.setStatus(securityRouteEntity.getStatus());
            securityRouteForms.add(securityRouteForm);
        }
        return securityRouteForms;
    }

    public static SecurityRouteMetaForm buildSecurityRouteMetaForm(SecurityRouteEntity securityRouteEntity) {
        SecurityRouteMetaForm securityRouteMetaForm = new SecurityRouteMetaForm();
        if (securityRouteEntity == null) {
            return securityRouteMetaForm;
        }
        securityRouteMetaForm.setTitle(securityRouteEntity.getTitle());
        securityRouteMetaForm.setIcon(securityRouteEntity.getIcon());
        return securityRouteMetaForm;
    }

}
