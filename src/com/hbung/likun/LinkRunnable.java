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
    OnReadDataListener onReadDataListener;
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
            String info = null;
            while ((info = buffR.readLine()) != null) {
                System.out.println("客户端说：" + info);
                clientInfo.addMessage(info);
                if (onReadDataListener != null) {
                    if (onReadDataListener.isStart()) {
                        onReadDataListener.onRead(clientInfo);
                    } else {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("客户端" + clientInfo.getName() + "已经断开连接");
        } finally {
            try {
                if (is != null)
                    is.close();
                if (os != null)
                    os.close();
                if (buffR != null)
                    buffR.close();
                close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() throws IOException {
        //断开连接
        if (socket != null) {
            send("stop");
            socket.close();
            socket = null;
        }
    }

    public void send(String message) {
        if (socket != null) {
            PrintWriter printWriter = null;
            try {
                printWriter = new PrintWriter(socket.getOutputStream(), true);
                printWriter.write(message);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (printWriter != null)
                    printWriter.close();
            }
        }
    }

    public interface OnReadDataListener {
        void onRead(ClientInfo clientInfo);

        boolean isStart();
    }


}
