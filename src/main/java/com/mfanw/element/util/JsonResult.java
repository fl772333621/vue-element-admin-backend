package com.mfanw.element.util;

import com.mfanw.element.enums.EnumErrorResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 返回用，JSON响应对象体
 *
 * @author mengwei
 */
@Getter
@Setter
@ToString
public class JsonResult {

    /**
     * 默认的成功的code码
     */
    private static final int CODE_SUCCESS = 20000;

    private int code = CODE_SUCCESS;

    private Object data;

    private String message = "成功";

    public static JsonResult success(Object data) {
        JsonResult result = new JsonResult();
        result.setData(data);
        return result;
    }

    public static JsonResult fail(EnumErrorResult errorResult) {
        JsonResult result = new JsonResult();
        result.setCode(errorResult.getCode());
        result.setMessage(errorResult.getMessage());
        return result;
    }
}
