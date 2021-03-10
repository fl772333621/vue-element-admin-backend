package com.mfanw.element.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mfanw.element.util.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 交易Controller
 *
 * @author mengwei
 */
@Controller
@RequestMapping("/vue-element-admin/transaction")
public class TransactionController {

    @GetMapping("/list")
    @ResponseBody
    public JsonResult list() {
        Map<String, Object> data = Maps.newHashMap();
        data.put("total", 13);
        List<Map<String, Object>> items = Lists.newArrayList();
        String[] status = new String[]{"pending", "success"};
        for (int i = 0; i < 13; i++) {
            Map<String, Object> item = Maps.newHashMap();
            item.put("order_no", UUID.randomUUID().toString());
            item.put("timestamp", System.currentTimeMillis());
            item.put("username", UUID.randomUUID().toString().substring(0, 5));
            item.put("price", 999.99);
            item.put("status", status[i % status.length]);
            items.add(item);
        }
        data.put("items", items);
        return JsonResult.success(data);
    }
}
