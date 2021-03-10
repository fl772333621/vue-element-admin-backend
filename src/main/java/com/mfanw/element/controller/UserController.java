package com.mfanw.element.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mfanw.element.configuration.jwt.JwtUserDetailsServiceImpl;
import com.mfanw.element.enums.EnumErrorResult;
import com.mfanw.element.util.JsonResult;
import com.mfanw.element.util.JwtTokenUtil;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户Controller
 *
 * @author mengwei
 */
@Controller
@RequestMapping("/vue-element-admin/user")
public class UserController {

    @Autowired
    private JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    @ResponseBody
    public JsonResult login(@RequestBody JSONObject jsonParam) {
        String username = MapUtils.getString(jsonParam, "username", "admin");
        String password = MapUtils.getString(jsonParam, "password", "admin");
        final UserDetails userDetails = jwtUserDetailsServiceImpl.loadUserByUsername(username);
        if (username.length() != password.length()) {
            return JsonResult.fail(EnumErrorResult.LOGIN_PASSWORD_ERROR);
        }
        Map<String, String> data = Maps.newHashMap();
        data.put("token", jwtTokenUtil.generateToken(userDetails));
        return JsonResult.success(data);
    }

    @PostMapping("/logout")
    @ResponseBody
    public JsonResult logout(@RequestBody(required = false) JSONObject jsonParam) {
        return JsonResult.success("success");
    }

    @GetMapping("/info")
    @ResponseBody
    public JsonResult info(String token) {
        Map<String, Object> data = Maps.newHashMap();
        data.put("roles", Lists.newArrayList("admin"));
        data.put("introduction", "I am a super administrator");
        data.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        data.put("name", "super admin");
        return JsonResult.success(data);
    }
}
