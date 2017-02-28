package com.hbung.likun;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Administrator on 2017/2/17.
 */
public class LinkRunnable implements Runnable {
    Socket socket;
    InetAddress address;
    StringBuffer stringBuffer = new StringBuffer();
    OnReadDataListener onReadDataListener;
    String clientName;
    ClientInfo clientInfo;

    public void setOnReadDataListener(OnReadDataListener onReadDataListener) {
        this.onReadDataListener = onReadDataListener;
    }

    public LinkRunnable(Socket socket) {
        this.socket = socket;
        clientInfo = new ClientInfo();
        address = socket.getInetAddress();//获取客户端地址
        clientInfo.setIp(address.getHostAddress());
        clientInfo.setName(address.getHostName());
    }

    @Override
    public void run() {
        InputStream is = null;
        OutputStream os = null;
        BufferedReader buffR = null;
        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();
            buffR = new BufferedReader(new InputStreamReader(is));
            while (true) {//读取数据
                String info = null;
                while ((info = buffR.readLine()) != null) {
                    System.out.println("我是服务器，客户端说：" + info);
                    clientInfo.addMessage(info);
                    if (onReadDataListener != null) {
                        onReadDataListener.onRead(clientInfo);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
                if (os != null)
                    os.close();
                if (buffR != null)
                    buffR.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public interface OnReadDataListener {
        void onRead(ClientInfo clientInfo);
    }


}
