package com.hbung.likun;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

/**
 * Created by Administrator on 2017/2/18 0018.
 */
public class SocketServiceRunable implements Runnable, LinkRunnable.OnReadDataListener {
    ServerSocket ss = null;
    private boolean isStart = false;
    OnCallback callback;

    public void setCallback(OnCallback callback) {
        this.callback = callback;
    }

    @Override
    public void run() {
        try {
            ss = new ServerSocket(1867);
            isStart = true;
            if (callback != null) callback.onStart();
            while (true) {//等待客户端连接
                Socket sk = ss.accept();
                LinkRunnable runnable = new LinkRunnable(sk);
                runnable.setOnReadDataListener(SocketServiceRunable.this);
                if (callback != null) callback.clientLink(runnable);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startOrStopService(ExecutorService executorService) {
        if (!isStart) {
            executorService.execute(this);
        } else if (ss != null) {
            try {
                ss.close();
                isStart = false;
                if (callback != null) callback.onStop();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void onRead(String clienName, StringBuffer s) {
        if (callback != null) callback.onRead(clienName, s);
    }

    public interface OnCallback {
        void onStart();

        void onStop();

        void onRead(String name, StringBuffer s);

        void clientLink(Runnable runnable);
    }
}
