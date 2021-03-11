package com.mfanw.element.controller;

import com.alibaba.fastjson.JSONObject;
import com.mfanw.element.dao.mapper.SecurityRouteMapper;
import com.mfanw.element.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户Controller
 *
 * @author mengwei
 */
@Controller
@RequestMapping("/vue-element-admin")
public class SecurityRouteController {

    @Autowired
    private SecurityRouteMapper securityRouteMapper;

    @GetMapping("/routes")
    @ResponseBody
    public JsonResult routes(@RequestBody(required = false) JSONObject jsonParam) {
        return JsonResult.success(securityRouteMapper.getList());
    }

}
