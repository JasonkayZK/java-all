package io.github.jasonkayzk;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {

    public static void send(String str) throws IOException {
        try (Socket client = new Socket("localhost", 8848)) {
            OutputStream outputStream = client.getOutputStream();
            outputStream.write(str.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();

            InputStream inputStream = client.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String info;

            try {
                while ((info = br.readLine()) != null) {
                    System.out.println("我是客户端，服务器返回信息：" + info);
                }
            } catch (IOException e) {
                System.out.printf("io error: %s\n", e);
            } finally {
                br.close();
                outputStream.close();
                inputStream.close();
                client.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        send("client test");
    }

}
