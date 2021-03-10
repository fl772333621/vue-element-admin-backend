package com.mfanw.element.configuration.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户Service <br />
 * 在此添加数据库支持即可完成用户及角色依赖数据库
 *
 * @author mengwei
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        LOGGER.warn(user);
        // 角色配置
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new SecurityUserDetails(user, authorityList);
    }

}