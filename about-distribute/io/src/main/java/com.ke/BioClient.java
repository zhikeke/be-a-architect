package com.ke;

import java.io.OutputStream;
import java.net.Socket;

public class BioClient {

    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("localhost", 80);

        //2、获取输出流，向服务器端发送信息
        OutputStream os = socket.getOutputStream();
        os.write("Hello".getBytes());

        os.flush();
        os.close();
    }

}
