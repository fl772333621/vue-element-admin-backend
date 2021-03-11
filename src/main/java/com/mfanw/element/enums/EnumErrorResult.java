package com.mfanw.element.enums;

/**
 * 网站错误码集合
 *
 * @author mengwei
 */

public enum EnumErrorResult {

    /**
     * 登陆密码错误
     */
    LOGIN_PASSWORD_ERROR(1, "密码错误，请重新输入！"),
    /**
     * 用户名未找到
     */
    USERNAME_NOT_FOUND(2, "用户名未找到！"),
    /**
     * 用户未配置角色
     */
    USER_ROLE_MISSING(3, "用户未配置角色！"),
    ;

    private final int code;

    private final String message;

    EnumErrorResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public EnumErrorResult getEnum(int code) {
        for (EnumErrorResult enumErrorResult : values()) {
            if (enumErrorResult.code == code) {
                return enumErrorResult;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
