package com.kate;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread
{
    String line=null;
    Socket s=null;
    InputStream sin = null;
    OutputStream sout = null;
    DataInputStream in = null;
    DataOutputStream out = null;

    public ServerThread(Socket s){
        this.s=s;
    }

    public void run() {
        try {
            sin = s.getInputStream();
            sout = s.getOutputStream();

            in = new DataInputStream(sin);
            out = new DataOutputStream(sout);

        }

        catch(IOException e){
            System.out.println("IO error in server thread");
        }

        try {
            line = in.readUTF();
            while( true) {
                out.writeUTF(line);
                out.flush();
                System.out.println("Response to Client  :  " + line);
                line = in.readUTF();
            }
        }

        catch (IOException e) {
            line=Thread.currentThread().getName(); //reused String line for getting thread name
            System.out.println("IO Error/ Client "+line+" terminated abruptly");
        }

        catch(NullPointerException e){
            line=Thread.currentThread().getName(); //reused String line for getting thread name
            System.out.println("Client "+line+" Closed");
        }


    }
}


