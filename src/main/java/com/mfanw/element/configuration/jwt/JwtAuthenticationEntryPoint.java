package com.mfanw.element.configuration.jwt;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.mfanw.element.enums.EnumErrorResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 认证入口点<br />
 * 自定义返回结果：未登录或登录过期
 *
 * @author mengwei
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException {
        LOGGER.warn("JwtAuthenticationEntryPoint:" + authException.getMessage());
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Map<String, Object> data = Maps.newHashMap();
        // 参考前端项目 vue-element-admin/src/utils/request.js 文件内的描述内容
        // 50008: Illegal token; 50012: Other clients logged in; 50014: Token expired;
        data.put("code", EnumErrorResult.ILLEGAL_TOKEN.getCode());
        data.put("message", EnumErrorResult.ILLEGAL_TOKEN.getMessage());
        response.getWriter().println(JSON.toJSONString(data));
        response.getWriter().flush();
    }
}