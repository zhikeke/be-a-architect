package com.ke;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 描述: ServerSocket
 *
 * @author MojiangHua
 * @create 2019-01-11 下午 12:38
 */
public class User_Skeleton extends Thread{
    private UserServer userServer;

    public User_Skeleton(UserServer userServer) {
        this.userServer = userServer;
    }

    @Override
    public void run() {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(8888);

            Socket socket = serverSocket.accept();

            while (socket != null) {
                // 获取客户端所要返回的方法
                ois = new ObjectInputStream(socket.getInputStream());
                String methodName = (String) ois.readObject();

                if (methodName != null && methodName.equals("getAge")) {
                    // 调用userServer 服务获取信息
                    int age = userServer.getAge();

                    // 返回给客户端
                    oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeInt(age);
                    oos.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
