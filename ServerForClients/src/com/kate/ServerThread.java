package com.kate;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class ServerThread extends Thread
{
    String line= null;
    Socket s;
    DataInputStream in = null;
    DataOutputStream out = null;
    Date today = null;
    String httpResponse = null;

    public ServerThread(Socket s){
        this.s=s;
    }

    public void run(){
        try {
            in = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());
            InputStreamReader isr = new InputStreamReader(s.getInputStream());
            BufferedReader reader = new BufferedReader(isr);
            line = reader.readLine();
            line=null;
        }

        catch(IOException e){
            System.out.println("IO error in server thread");
        }

        try {
            today = new Date();
            httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today;
            out.writeUTF(httpResponse);
            while( true) {
                line = in.readUTF();
                out.writeUTF(line);
                out.flush();
                System.out.println("Response to Client  :  " + line);
            }
        }

        catch (IOException e) {
            line=Thread.currentThread().getName();
            System.out.println("IO Error/ Client "+line+" terminated abruptly");
        }

        catch(NullPointerException e){
            line=Thread.currentThread().getName();
            System.out.println("Client "+line+" Closed");
        }


    }
}


