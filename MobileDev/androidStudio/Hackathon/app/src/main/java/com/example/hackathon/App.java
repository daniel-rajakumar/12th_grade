package com.example.hackathon;

import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;

import java.util.Timer;
import java.util.TimerTask;

public class App {
    Vibrator vibrator;

    App(MainActivity mainActivity){
        vibrator = (Vibrator) mainActivity.getSystemService(Context.VIBRATOR_SERVICE);

        vibrate();
    }

    void vibrate(){

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                vibrator.vibrate(VibrationEffect.createOneShot(1_000_000_000, VibrationEffect.DEFAULT_AMPLITUDE));
            }
        }, 0, 10000);
    }
}
