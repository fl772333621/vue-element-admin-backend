package com.mfanw.element.controller;

import com.mfanw.element.configuration.jwt.JwtUserDetailsServiceImpl;
import com.mfanw.element.dao.mapper.SecurityUserMapper;
import com.mfanw.element.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Hello Controller
 *
 * @author mengwei
 */
@Api(tags = "JWT 演示接口")
@Controller
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private SecurityUserMapper securityUserMapper;

    @ApiOperation("登陆请求")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户", dataType = "string", required = true, paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "string", required = true, paramType = "query")
    })
    @PostMapping("/doLogin")
    @ResponseBody
    public String doLogin(HttpServletRequest request, String username, String password) {
        final UserDetails userDetails = jwtUserDetailsServiceImpl.loadUserByUsername(username);
        if (username.length() != password.length()) {
            return "密码错误，请重新输入！";
        }
        return jwtTokenUtil.generateToken(userDetails);
    }

    @ApiOperation("hello请求")
    @PostMapping("/hello")
    @ResponseBody
    public String hello() {
        return "已经登陆，玩的开心~";
    }

    @ApiOperation("helloPermitAll请求")
    @PostMapping("/helloPermitAll")
    @ResponseBody
    public String helloPermitAll() {
        LOGGER.warn(securityUserMapper.getByUsername("admin").toString());
        return "hello 陌生人~";
    }
}
