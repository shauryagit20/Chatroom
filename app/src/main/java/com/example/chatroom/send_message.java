package com.example.chatroom;

import android.app.Notification;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class send_message extends Thread {
    Button send;
    DataOutputStream output;
    DataInputStream input;
    EditText message;
    Socket s;

    public send_message(Button send, EditText message, Socket s) {
        this.send =  send;
        this.message =  message;
        this.s =  s;
    }

    @Override
    public void run()
    {

        try
        {

            output=new DataOutputStream(s.getOutputStream());
            input = new DataInputStream(s.getInputStream());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text =  message.getText().toString();
                String len = Integer.toString(text.length());
                try {
                    Thread thread = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            try  {
                                output.writeUTF(len);
                                output.writeUTF(text);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    thread.start();
                    Log.d("Connected", "onCreate: Connected ");
                    message.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
