package com.kate;

import java.io.*;
import java.net.*;

public class ServerForClients {
    public static void main(String[] args) throws IOException {

        Socket socket;
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Listening for connection on port 8080 ....");

        while(true)
        {
            socket = serverSocket.accept();
            System.out.println("Connection established");
            ServerThread st = new ServerThread(socket);
            st.start();
        }

    }


}
