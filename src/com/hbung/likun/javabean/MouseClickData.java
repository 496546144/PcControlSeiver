package com.hbung.likun.javabean;

/**
 * 作者　　: 李坤
 * 创建时间:2017/3/1　16:26
 * 邮箱　　：496546144@qq.com
 * <p>
 * 功能介绍：
 */

public class MouseClickData extends BaseData{
    boolean isLeft;
    boolean isClick;//是否单击

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    public boolean isLeft() {
        return isLeft;
    }

    public void setLeft(boolean left) {
        isLeft = left;
    }
}
