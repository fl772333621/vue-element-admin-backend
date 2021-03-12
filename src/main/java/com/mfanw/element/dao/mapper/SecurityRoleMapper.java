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
     * 新增
     *
     * @param securityRoleEntity 待新增实体
     */
    void insert(SecurityRoleEntity securityRoleEntity);

    /**
     * 删除
     *
     * @param key 待删除实体主键
     */
    void delete(Long key);

    /**
     * 更新
     *
     * @param securityRoleEntity 待更新实体
     */
    void update(SecurityRoleEntity securityRoleEntity);

    /**
     * 根据角色ID获取角色实体
     *
     * @param key 角色key
     * @return 角色实体
     */
    SecurityRoleEntity getById(long key);

    /**
     * 获取列表
     *
     * @return 列表结果
     */
    List<SecurityRoleEntity> getList();

    /**
     * 根据用户ID获取列表
     *
     * @param userId 用户ID
     * @return 列表结果
     */
    List<SecurityRoleEntity> getListByUserId(long userId);

}
