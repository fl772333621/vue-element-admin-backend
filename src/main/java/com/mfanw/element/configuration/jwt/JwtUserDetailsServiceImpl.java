package com.mfanw.element.configuration.jwt;

import com.mfanw.element.dao.entity.SecurityRoleEntity;
import com.mfanw.element.dao.entity.SecurityUserEntity;
import com.mfanw.element.dao.mapper.SecurityRoleMapper;
import com.mfanw.element.dao.mapper.SecurityUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户Service <br />
 * 在此添加数据库支持即可完成用户及角色依赖数据库
 *
 * @author mengwei
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUserDetailsServiceImpl.class);

    @Autowired
    private SecurityUserMapper securityUserMapper;

    @Autowired
    private SecurityRoleMapper securityRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.warn(username);
        SecurityUserEntity securityUserEntity = securityUserMapper.getByUsername(username);
        if (securityUserEntity == null) {
            LOGGER.warn(username + ",无法找到用户");
            return null;
        }
        // 角色配置
        List<SecurityRoleEntity> roles = securityRoleMapper.getListByUserId(securityUserEntity.getId());
        List<GrantedAuthority> authorityList = new ArrayList<>();
        if (CollectionUtils.isEmpty(roles)) {
            authorityList.addAll(roles.stream().map(SecurityRoleEntity::getKey).map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        }
        // 返回对象
        SecurityUserDetails securityUserDetails = new SecurityUserDetails(securityUserEntity.getUsername(), authorityList);
        securityUserDetails.setPassword(securityUserEntity.getPassword());
        return securityUserDetails;
    }

}