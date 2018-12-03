package com.kate;

import java.io.*;
import java.net.Socket;

public class Client1 {
    public static void main(String[] args) throws IOException {
        Socket socket;
        socket = new Socket("localhost", 8090);
        System.out.println(socket.getInetAddress());

        InputStream sin = socket.getInputStream();
        OutputStream sout = socket.getOutputStream();

        DataInputStream in = new DataInputStream(sin);
        DataOutputStream out = new DataOutputStream(sout);

        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        System.out.println("Type in something and press enter.");
        System.out.println();

        while (true) {
            line = keyboard.readLine();
            System.out.println("Sending this line to the server...");
            out.writeUTF(line);
            out.flush();
            line = in.readUTF();
            System.out.println("The server sent me this : " + line);
            System.out.println("You can enter more lines.");
            System.out.println();
        }
    }
}
