package com.mfanw.element.form;

import com.alibaba.druid.support.json.JSONUtils;

import java.io.Serializable;

/**
 * 基础数据结构
 *
 * @author mengwei
 */
public class BaseForm implements Serializable {

    private static final long serialVersionUID = 5188936337858084402L;

    @Override
    public String toString() {
        return JSONUtils.toJSONString(this);
    }
}
