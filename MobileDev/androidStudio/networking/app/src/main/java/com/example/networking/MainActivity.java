package com.example.networking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    Button button_server, button_client;
    EditText editText_server, editText_client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());

        button_client = findViewById(R.id.button_client);
        button_server = findViewById(R.id.button_server);
        editText_client = findViewById(R.id.editText_client);
        editText_server = findViewById(R.id.editText_server);


        button_server.setOnClickListener(v -> {
            setTitle(button_server.getText());
            button_client.setVisibility(View.GONE);
            editText_client.setVisibility(View.GONE);


            try {



                ServerSocket server = new ServerSocket(8080);
                Log.d("", "onCreate: " + "Sever Started");
                Log.d("", "onCreate: " + "Waiting for a clinet...");
                try (java.util.Scanner s = new java.util.Scanner(new java.net.URL("https://api64.ipify.org").openStream(), "UTF-8").useDelimiter("\\A")) {
                    System.out.println("My current IP address is " + s.next());
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }


                button_server.setText(button_server.getText() + "Server Started \n");
                button_server.setText(button_server.getText() + "Waiting for a client... \n");

                Socket socket = server.accept();
                button_server.setText(button_server.getText() + "Client accepted \n");
                Log.d("", "onCreate: " + "Client accepted");
                System.out.println("Client accepted");


            } catch (IOException e){ e.printStackTrace(); }

        });

        button_client.setOnClickListener(v -> {
            setTitle(button_client.getText());
            button_server.setVisibility(View.GONE);
            editText_server.setVisibility(View.GONE);

            try {
                Log.d("", "onCreate: " + "Connecting...");
                Socket socket = new Socket("10.0.0.126", 8080);
                Log.d("", "onCreate: " + "Connected");
                button_client.setText(button_client.getText() + "Connected \n");





            } catch (IOException e) { e.printStackTrace(); }
        });



    }
}