package com.mfanw.element.dao.mapper;

import com.mfanw.element.dao.entity.SecurityUserEntity;

/**
 * 用户Mapper
 *
 * @author mengwei
 */
public interface SecurityUserMapper {

    /**
     * 根据用户名称获取用户实体
     *
     * @param username 用户名称
     * @return 用户实体
     */
    SecurityUserEntity getByUsername(String username);

}
