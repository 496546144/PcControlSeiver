package com.hbung.likun;

import java.awt.*;

/**
 * Created by Administrator on 2017/2/28.
 */
public class HandelResult {
    Robot myRobot;
    JsonHelp jsonHelp;

    public HandelResult() {
        try {
            myRobot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        jsonHelp = new JsonHelp();
    }

    public void handel(String s) {
        if (s == null || s.equals("scan")) return;
        JavaBean javaBean = jsonHelp.get(s.toString());
        if (javaBean.action == 1) {//按键
            press(javaBean.action);
        }
    }

    public void press(int keyEvent) {
//        myRobot.keyPress(KeyEvent.VK_SHIFT);    // 模拟键盘按下shift键
        myRobot.keyPress(keyEvent);        // 模拟键盘按下Q键（小写）
        myRobot.keyRelease(keyEvent);      // 模拟键盘释放Q键
//        myRobot.keyRelease(KeyEvent.VK_SHIFT);  // 模拟键盘释放shift键
    }
    /**
     *        // 打出一个大写的Q
     //        myRobot.keyPress(KeyEvent.VK_SHIFT);    // 模拟键盘按下shift键
     //        myRobot.keyPress(KeyEvent.VK_Q);        // 模拟键盘按下Q键（小写）
     //        myRobot.keyRelease(KeyEvent.VK_Q);      // 模拟键盘释放Q键
     //        myRobot.keyRelease(KeyEvent.VK_SHIFT);  // 模拟键盘释放shift键
     // 移动鼠标到坐标（x,y)处，并点击左键
     myRobot.mouseMove(200, 300);                // 移动鼠标到坐标（x,y）处
     myRobot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);     // 模拟按下鼠标左键
     myRobot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);   // 模拟释放鼠标左键
     */
}
