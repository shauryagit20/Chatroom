package com.example.chatroom;

import android.util.Log;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.*;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class Recv_Message  extends Thread{
    public TextView text;
    public DataOutputStream output;
    public DataInputStream input;
    Socket s;
    String string_b = "";
    public Recv_Message(TextView text, Socket s) {
        this.text = text;
        this.s =  s;
    }
    @Override
    public void run()
    {
        Timer timer =  new Timer();
        text.setText("In side method");
        try
        {
            output=new DataOutputStream(s.getOutputStream());
            input = new DataInputStream(s.getInputStream());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        Log.d("RUN:", "run: Inside run");
        TimerTask taskk = new TimerTask() {
            @Override
            public void run() {
                try {
                    Log.d("printing:  ", "run: In the recv");
                    Log.d("Input", "run: "+  input.available());
                        string_b =  input.readUTF();
                        text.append(string_b);


                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        };
        timer.schedule(taskk,200,200);

    }
}
