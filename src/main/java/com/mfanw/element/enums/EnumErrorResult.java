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
    /**
     * 50008: Illegal token（参考前端项目 vue-element-admin/src/utils/request.js 文件内的描述内容）
     */
    ILLEGAL_TOKEN(50008, "非法的凭证"),
    /**
     * 50012: Other clients logged in（参考前端项目 vue-element-admin/src/utils/request.js 文件内的描述内容）
     */
    OTHER_CLIENTS_LOGGED_IN(50012, "其他客户端已经登陆"),
    /**
     * 50014: Token expired（参考前端项目 vue-element-admin/src/utils/request.js 文件内的描述内容）
     */
    TOKEN_EXPIRED(50014, "凭证已经过期"),

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
