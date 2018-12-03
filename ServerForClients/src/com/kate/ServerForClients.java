package com.kate;

import java.io.IOException;
import java.net.*;

public class ServerForClients {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket;
        Socket socket;
        serverSocket = new ServerSocket(8090);

        while(true)
        {
            socket = serverSocket.accept();
            System.out.println("Connection established");
            ServerThread st = new ServerThread(socket);
            st.start();
        }

    }

}
