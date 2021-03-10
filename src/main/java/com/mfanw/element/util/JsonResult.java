package com.mfanw.element.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author mengwei
 */
@Getter
@Setter
@ToString
public class JsonResult {

    private static final int CODE_SUCCESS = 20000;

    private int code = CODE_SUCCESS;

    private Object data;

    private String message;

    public static JsonResult success(Object data) {
        JsonResult result = new JsonResult();
        result.setData(data);
        return result;
    }

    public static JsonResult fail(int code, String message) {
        JsonResult result = new JsonResult();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}
