package com.mfanw.element.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mengwei
 */
public class ThreadLocalUtil extends ThreadLocal<Map<String, Object>> {

    private static final ThreadLocalUtil INSTANCE = new ThreadLocalUtil();

    @Override
    protected Map<String, Object> initialValue() {
        return new HashMap<>(15);
    }

    public static ThreadLocalUtil getInstance() {
        return INSTANCE;
    }

    public Object get(String key) {
        return this.get().get(key);
    }

    public void set(String key, Object value) {
        this.get().put(key, value);
    }


    public void setUsername(String username) {
        set("username", username);
    }

    public String getUsername() {
        Object obj = get("username");
        return (obj != null) ? (String) obj : "";
    }
}