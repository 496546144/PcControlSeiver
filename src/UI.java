import com.hbung.likun.SocketServiceRunable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UI implements ActionListener, SocketServiceRunable.OnCallback {
    private JPanel panelRoot;
    private JButton startButtom;
    private JTextPane textPane;
    private JLabel clienName;
    private Robot myRobot = null;
    private JScrollPane jScrollPane;
    ExecutorService executorService = null;

    SocketServiceRunable socketServiceRunable = null;

    public static void main(String[] args) {
        JFrame frame = new JFrame("李坤");
        JPanel jPanel = new UI().panelRoot;
        frame.setContentPane(jPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(350, 500);
        frame.setLocationRelativeTo(jPanel);//居中
        frame.setVisible(true);


    }

    void createUIComponents() {
        panelRoot = new JPanel();
        startButtom = new JButton();
        textPane = new JTextPane();
        clienName = new JLabel();
        jScrollPane = new JScrollPane(textPane);
        startButtom.addActionListener(this);
        try {
            myRobot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        executorService = Executors.newCachedThreadPool();
        socketServiceRunable = new SocketServiceRunable();
        socketServiceRunable.setCallback(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // 打出一个大写的Q
//        myRobot.keyPress(KeyEvent.VK_SHIFT);    // 模拟键盘按下shift键
//        myRobot.keyPress(KeyEvent.VK_Q);        // 模拟键盘按下Q键（小写）
//        myRobot.keyRelease(KeyEvent.VK_Q);      // 模拟键盘释放Q键
//        myRobot.keyRelease(KeyEvent.VK_SHIFT);  // 模拟键盘释放shift键
        // 移动鼠标到坐标（x,y)处，并点击左键
        myRobot.mouseMove(200, 300);                // 移动鼠标到坐标（x,y）处
        myRobot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);     // 模拟按下鼠标左键
        myRobot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);   // 模拟释放鼠标左键

        socketServiceRunable.startOrStopService(executorService);
    }


    @Override
    public void onRead(String name, StringBuffer s) {
        textPane.setText(s.toString());
        clienName.setText(name);
        postBottom();
        press(KeyEvent.VK_6);
    }

    private void postBottom() {
        JScrollBar scrollBar = jScrollPane.getVerticalScrollBar();
        scrollBar.setValue(scrollBar.getMaximum());
    }

    @Override
    public void clientLink(Runnable runnable) {
        executorService.execute(runnable);
    }

    public void press(int keyEvent) {
//        myRobot.keyPress(KeyEvent.VK_SHIFT);    // 模拟键盘按下shift键
        myRobot.keyPress(keyEvent);        // 模拟键盘按下Q键（小写）
        myRobot.keyRelease(keyEvent);      // 模拟键盘释放Q键
//        myRobot.keyRelease(KeyEvent.VK_SHIFT);  // 模拟键盘释放shift键
    }

    @Override
    public void onStart() {
        startButtom.setText("关闭服务");
    }

    @Override
    public void onStop() {
        startButtom.setText("开启服务");
    }
}
