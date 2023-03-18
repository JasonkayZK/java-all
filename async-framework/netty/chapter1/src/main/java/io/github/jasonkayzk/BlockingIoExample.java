package io.github.jasonkayzk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Listing 1.1 Blocking I/O example
 */
public class BlockingIoExample {

    public static Integer port = 14444;

    /**
     * Listing 1.1 Blocking I/O example
     */
    public void serve(int portNumber) throws IOException {
        while (true) {
            Socket clientSocket;
            try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
                clientSocket = serverSocket.accept();
            } catch (SocketException e) {
                System.out.println("Connect client socket failed: " + e.getMessage());
                throw e;
            }
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out =
                    new PrintWriter(clientSocket.getOutputStream(), true);
            String request, response;

            while ((request = in.readLine()) != null) {
                System.out.println("Request: " + request);

                if ("Done".equals(request)) {
                    break;
                }
                response = processRequest(request);
                out.println(response);
            }
        }
    }

    private String processRequest(String request) {
        return request + " Processed";
    }

    public static void main(String[] args) throws IOException {
        new BlockingIoExample().serve(port);
    }

}

class BlockingIoClient {

    /**
     * Listing 1.1 Blocking I/O example
     */
    public void send(String message) throws IOException {
        BufferedReader in;
        PrintWriter out;
        try (Socket clientSocket = new Socket("127.0.0.1", BlockingIoExample.port)) {
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(message);
            System.out.println(in.readLine());
        } catch (IOException e) {
            System.out.println("Connect server socket failed: " + e.getMessage());
            throw e;
        }
    }

    public static void main(String[] args) throws IOException {
        new BlockingIoClient().send("haha");
    }

}
