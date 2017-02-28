import com.hbung.likun.HandelResult;
import com.hbung.likun.SocketServiceRunable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UI implements ActionListener, SocketServiceRunable.OnCallback {
    private JPanel panelRoot;
    private JButton startButtom;
    private JTextPane textPane;
    private JLabel clienName;
    private JScrollPane jScrollPane;
    ExecutorService executorService = null;
    HandelResult handelResult ;
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

        handelResult = new HandelResult();
        executorService = Executors.newCachedThreadPool();
        socketServiceRunable = new SocketServiceRunable();
        socketServiceRunable.setCallback(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        socketServiceRunable.startOrStopService(executorService);
    }


    @Override
    public void onRead(String name, StringBuffer s) {
        textPane.setText(s.toString() + "\n");
        clienName.setText(name);
        postBottom();
        handelResult.handel(s.toString());
    }

    private void postBottom() {
        JScrollBar scrollBar = jScrollPane.getVerticalScrollBar();
        scrollBar.setValue(scrollBar.getMaximum());
    }

    @Override
    public void clientLink(Runnable runnable) {
        executorService.execute(runnable);
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
