package com.mfanw.element.configuration;

import com.mfanw.element.consts.CommonConst;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ResponseHeaderFilter 添加header
 *
 * @author mengwei
 */
@Component
public class ResponseHeaderFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE");
        // x-token 字段为鉴权专用字段
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,x-token,XFILENAME,XFILECATEGORY,XFILESIZE");
        if (StringUtils.equals(request.getMethod(), CommonConst.OPTIONS)) {
            response.setStatus(HttpStatus.OK.value());
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}