package com.mfanw.element.controller;

import com.mfanw.element.configuration.jwt.JwtUserDetailsServiceImpl;
import com.mfanw.element.dao.mapper.SecurityUserMapper;
import com.mfanw.element.enums.EnumErrorResult;
import com.mfanw.element.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private final JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;

    @Autowired
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    private final SecurityUserMapper securityUserMapper;

    @Autowired
    private final PasswordEncoder passwordEncoderBean;

    public HelloController(JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl, JwtTokenUtil jwtTokenUtil,
                           SecurityUserMapper securityUserMapper, PasswordEncoder passwordEncoderBean) {
        this.jwtUserDetailsServiceImpl = jwtUserDetailsServiceImpl;
        this.jwtTokenUtil = jwtTokenUtil;
        this.securityUserMapper = securityUserMapper;
        this.passwordEncoderBean = passwordEncoderBean;
    }

    @ApiOperation("登陆请求")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户", dataType = "string", required = true, paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "string", required = true, paramType = "query")
    })
    @PostMapping("/doLogin")
    @ResponseBody
    public String doLogin(HttpServletRequest request, String username, String password) {
        // 加载用户
        final UserDetails userDetails = jwtUserDetailsServiceImpl.loadUserByUsername(username);
        if (userDetails == null) {
            return EnumErrorResult.USERNAME_NOT_FOUND.getMessage();
        }
        // 登陆密码校验
        if (!passwordEncoderBean.matches(password, userDetails.getPassword())) {
            return EnumErrorResult.LOGIN_PASSWORD_ERROR.getMessage();
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
        return "hello 陌生人~" + securityUserMapper.getByUsername("admin").toString();
    }
}
