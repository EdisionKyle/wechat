package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 说明：测试
 * 创建时间：2017年06月11日 23:40
 * Copyright (C) 2017, tianpc0318@163.com All Rights Reserved.
 *
 * @author milesloner
 */
public class JsonUtil {

    public static Map<String, Object> parse(String name, int age, String addr) {
        Map<String, Object> res = new HashMap<>();
        res.put("name", name);
        res.put("age", age);
        res.put("addr", addr);
        return res;
    }

}
