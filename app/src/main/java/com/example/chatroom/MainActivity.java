package com.example.chatroom;

import androidx.appcompat.app.AppCompatActivity;
import java.io.*;
import java.net.*;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Socket s =  null;
        DataInputStream input;
        DataOutputStream out;
        TextView text = (TextView) findViewById(R.id.chatbox);
        Button send =  (Button) findViewById(R.id.Send);
        text.append("helli");
        EditText message = (EditText) findViewById(R.id.Message);
        try {
            s =  new Socket("192.168.1.31", 8080);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Log.d("Try;", "onCreate: In the try method");
            Recv_Message obj =  new Recv_Message(text,s);
            obj.start();
            Log.d("check", "onCreate: Star");
            send_message obj1 =  new send_message(send,message,s);
            obj1.start();

        } catch (Exception e) {
            Log.d("e", "onCreate: Errot");
            e.printStackTrace();
        }

    }
}