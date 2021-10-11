package com.example.test_layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button1, button2;
    TextView textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

        button1.setOnClickListener(new View.OnClickListener() {
            boolean toggle = false;
            @Override
            public void onClick(View view) {
                if (toggle) textView1.setText("OFF              ");
                else        textView1.setText("NO                 ");

                toggle = !toggle;
            }
        });

        button2.setOnClickListener(this);
    }

    boolean toggle = false;
    @Override
    public void onClick(View view) {

        if (toggle) textView2.setText("              OFF");
        else        textView2.setText("                 NO");

        toggle = !toggle;
    }
}
