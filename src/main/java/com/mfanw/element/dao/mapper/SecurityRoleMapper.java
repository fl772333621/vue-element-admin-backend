package com.mfanw.element.dao.mapper;

import com.mfanw.element.dao.entity.SecurityRoleEntity;

import java.util.List;

/**
 * 角色Mapper
 *
 * @author mengwei
 */
public interface SecurityRoleMapper {

    void insert(SecurityRoleEntity securityRoleEntity);

    void delete(Long key);

    void update(SecurityRoleEntity securityRoleEntity);

    /**
     * 根据角色ID获取角色实体
     *
     * @param key 角色key
     * @return 角色实体
     */
    SecurityRoleEntity getById(long key);

    List<SecurityRoleEntity> getList();

    List<SecurityRoleEntity> getListByUserId(long userId);

}
