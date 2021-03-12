package com.mfanw.element.configuration.jwt;

import com.mfanw.element.consts.CommonConst;
import com.mfanw.element.util.JwtTokenUtil;
import com.mfanw.element.util.ThreadLocalUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mengwei
 */
@Component
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthorizationTokenFilter.class);

    @Autowired
    private final JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;

    @Autowired
    private final JwtTokenUtil jwtTokenUtil;

    private final String tokenHeader;

    public JwtAuthorizationTokenFilter(JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl,
                                       JwtTokenUtil jwtTokenUtil,
                                       @Value("${jwt.token}") String tokenHeader) {
        this.jwtUserDetailsServiceImpl = jwtUserDetailsServiceImpl;
        this.jwtTokenUtil = jwtTokenUtil;
        this.tokenHeader = tokenHeader;
        LOGGER.warn(tokenHeader);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 对于authToken字段：
        // SecurityConfig.configure()内配置了/hello需要权限校验，所以request.getHeader(TOKEN)必定有值，如果没有获取到""
        // SecurityConfig.configure()内配置了/helloPermitAll不需要权限校验，所以request.getHeader(TOKEN)必定为null
        final String authToken = getToken(request);
        LOGGER.warn("Method={}, URI={}, token={}", request.getMethod(), request.getRequestURI(), authToken);
        if (!StringUtils.equals(request.getMethod(), CommonConst.OPTIONS) && authToken != null) {
            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            ThreadLocalUtil.getInstance().setUsername(username);
            LOGGER.warn("username={}", username);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = jwtUserDetailsServiceImpl.loadUserByUsername(username);
                LOGGER.warn("userDetails={}", userDetails);
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        if (StringUtils.equals(request.getMethod(), CommonConst.OPTIONS)) {
            return null;
        }
        String token = request.getHeader(this.tokenHeader);
        if (StringUtils.isBlank(token)) {
            return request.getParameter("token");
        }
        return token;
    }
}
