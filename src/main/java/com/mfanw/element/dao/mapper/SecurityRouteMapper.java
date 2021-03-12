package com.mfanw.element.dao.mapper;

import com.mfanw.element.dao.entity.SecurityRouteEntity;

import java.util.List;

/**
 * 角色Mapper
 *
 * @author mengwei
 */
public interface SecurityRouteMapper {

    /**
     * 根据角色ID获取角色实体
     *
     * @param id 角色ID
     * @return 角色实体
     */
    SecurityRouteEntity getById(long id);

    /**
     * 获取列表
     *
     * @return 列表结果
     */
    List<SecurityRouteEntity> getList();
}
