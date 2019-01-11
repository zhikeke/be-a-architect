package com.ke;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 描述: 服务接口
 *
 * @author MojiangHua
 * @create 2019-01-11 下午 12:38
 */
class User_Stub extends User{

    private Socket socket;

    public User_Stub() throws IOException {
        socket = new Socket("localhost", 8888);
    }

    @Override
    public int getAge() {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject("getAge");
            oos.flush();

            ois = new ObjectInputStream(socket.getInputStream());
            return ois.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return 0;
    }
}
