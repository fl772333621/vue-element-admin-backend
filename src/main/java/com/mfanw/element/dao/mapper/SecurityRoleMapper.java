package com.mfanw.element.dao.mapper;

import com.mfanw.element.dao.entity.SecurityRoleEntity;

import java.util.List;

/**
 * 角色Mapper
 *
 * @author mengwei
 */
public interface SecurityRoleMapper {

    /**
     * 根据角色ID获取角色实体
     *
     * @param id 角色ID
     * @return 角色实体
     */
    SecurityRoleEntity getById(long id);

    List<SecurityRoleEntity> getList();

    List<SecurityRoleEntity> getListByUserId(long userId);
}