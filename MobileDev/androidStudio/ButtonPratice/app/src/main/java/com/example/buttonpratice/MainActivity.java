package com.example.buttonpratice;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button button1, button2, button3, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        button1.setText("Color");
        button2.setText("Size");
        button3.setText("Swap");

        task1();
        task2();
        task3();
        extra1();
    }

    public void task1(){
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int r = (int)(Math.random() * 256),
                    g = (int)(Math.random() * 256),
                    b = (int)(Math.random() * 256);

                button1.setTextColor(Color.rgb(r, g, b));
            }
        });
    }

    public void task2(){
        button2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                button2.setTextSize(random(10, 30));
                int padding = random(0, 200);
                button2.setPadding(padding, padding, padding, padding);
            }

            int random(int min, int max){
                return (int)(Math.random() * (max - min + 1)) + min;
            }
        });
    }

    public void task3(){
        button3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                CharSequence button1Text = button1.getText();
                button1.setText(button2.getText());
                button2.setText(button1Text);
            }

            int random(int min, int max){
                return (int)(Math.random() * (max - min + 1)) + min;
            }
        });
    }


    void extra1(){
        button4.setOnClickListener(new ExtraButtonListener());
    }

    class ExtraButtonListener implements View.OnClickListener {
        boolean isDarkMode;

        ExtraButtonListener(){
            toggleDarkMode();
        }

        @Override
        public void onClick(View view) {
            toggleDarkMode();
        }

        public void toggleDarkMode(){
            int buttonTextColor, buttonBackgroundColor, layoutBackgroundColor;
            String buttonText;

            if (isDarkMode){
                buttonText = "Light mode";
                layoutBackgroundColor = Color.WHITE;
                buttonBackgroundColor = Color.DKGRAY;
                buttonTextColor = Color.WHITE;

            } else {
                buttonText = "Dark mode";
                layoutBackgroundColor = Color.DKGRAY;
                buttonBackgroundColor = Color.WHITE;
                buttonTextColor = Color.DKGRAY;
            }

            findViewById(R.id.linearLayout).setBackgroundColor(layoutBackgroundColor);
            button4.setText(buttonText);
            for (int i = 1; i <= 4; i++){
                Button button = findViewById(getResources().getIdentifier("button"+i, "id", getPackageName()));
                button.setBackgroundColor(buttonBackgroundColor);
                button.setTextColor(buttonTextColor);
            }

            isDarkMode = !isDarkMode;
        }

    }
}