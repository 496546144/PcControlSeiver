package com.hbung.likun;

import com.hbung.likun.javabean.KeyBean;
import com.hbung.likun.javabean.MoveData;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/2/28.
 */
public class JsonHelp {

    public KeyBean getKeyBean(String json) {
        KeyBean bean = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            int action = jsonObject.getInt("action");
            if (action == 1) {
                bean = new KeyBean();
                bean.code = jsonObject.getInt("code");
                bean.action = action;
            }
        } catch (Exception e) {
        }
        return bean;
    }

    public MoveData getMoveData(String json) {
        MoveData bean = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            int action = jsonObject.getInt("action");
            if (action == 2) {
                bean = new MoveData();
                bean.distanceX = jsonObject.getInt("distanceX");
                bean.distanceY = jsonObject.getInt("distanceY");
                bean.action = action;
            }
        } catch (Exception e) {
        }
        return bean;
    }

    public int getAction(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            int action = jsonObject.getInt("action");
            return action;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
