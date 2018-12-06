package com.kate;

import java.io.*;
import java.net.Socket;

public class Client1 {
    public static void main(String[] args) throws IOException {
        Socket socket;
        socket = new Socket("localhost", 8080);
        System.out.println(socket.getInetAddress());

        int port=socket.getLocalPort();
        String clientPort= Integer.toString(port);
        System.out.println("Local port: " + clientPort);

        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        boolean autoflush = true;
        PrintWriter cout = new PrintWriter(socket.getOutputStream(), autoflush);
        cout.println("GET / HTTP/1.1");

        String line = null;
        line = in.readUTF();
        System.out.println("The server sent me this : " + line);

        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Type in something and press enter.");

        while (true) {
            line = keyboard.readLine();
            System.out.println("Sending this line to the server...");
            out.writeUTF("Client port - " + clientPort + " - message: " +line);
            out.flush();
            line = in.readUTF();
            System.out.println("The server sent me this : " + line);
            System.out.println("You can enter more lines.");
            System.out.println();
        }
    }
}
