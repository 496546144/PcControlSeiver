package com.hbung.likun;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/2/28.
 */
public class JsonHelp {

    public JavaBean get(String json) {
        JavaBean bean = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            bean = new JavaBean();
            bean.code = jsonObject.getInt("code");
            bean.action = jsonObject.getInt("action");
        } catch (Exception e) {
        }
        return bean;
    }
}
