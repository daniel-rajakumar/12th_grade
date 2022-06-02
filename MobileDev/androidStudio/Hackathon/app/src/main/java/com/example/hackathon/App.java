package com.example.hackathon;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.VibrationEffect;
import android.os.Vibrator;

import java.util.Timer;
import java.util.TimerTask;

public class App {
    Vibrator vibrator;
    MediaPlayer mediaPlayer;
    Context context;

    App(MainActivity mainActivity){
        vibrator = (Vibrator) mainActivity.getSystemService(Context.VIBRATOR_SERVICE);
        mediaPlayer = MediaPlayer.create(mainActivity, R.raw.gameplay_music);
        vibrate();
        playMusic();
    }

    void playMusic(){
        mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(0.5f));
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
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
