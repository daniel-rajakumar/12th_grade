package com.example.surfaceview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener2;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {

    GameSurface gameSurface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        gameSurface = new GameSurface(this);
        setContentView(gameSurface);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameSurface.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameSurface.resume();
    }

    public class GameSurface extends SurfaceView implements Runnable, SensorEventListener2 {

        private static final String TAG = "run";
        Thread gameThread;
        SurfaceHolder holder;
        volatile boolean running = false;
        Bitmap ball;
        int ball_x = 0, ball_y = 0, ball_dy = 0;

        int screenWidth, screenHeight;

        Paint paintProperty;

        public GameSurface(Context context) {
            super(context);

            holder = getHolder();
            ball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);

            Display screenDisplay = getWindowManager().getDefaultDisplay();
            Point sizeOfScreen = new Point();
            screenDisplay.getSize(sizeOfScreen);

            screenWidth = sizeOfScreen.x;
            screenHeight = sizeOfScreen.y;

        }

        @Override
        public void onFlushCompleted(Sensor sensor) {

        }

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }

        @Override
        public void run() {
            int flip = 1;
            int count = 0;

            while (running){
                if (!holder.getSurface().isValid()){
                    continue;
                }

                Canvas canvas = holder.lockCanvas();
                canvas.drawRGB(0, 255, 0);
                int left = (screenWidth/2) - (ball.getWidth()/2);
                int top = (screenHeight / 2) - (ball.getHeight());


                canvas.save();
                canvas.rotate(count++);
                canvas.drawBitmap(ball, left + ball_x, top + ball_y, null);
                canvas.restore();



                if (ball_x == screenWidth/2 - ball.getWidth()/2 || ball_x == -1 * screenWidth/2 + ball.getWidth()/2) {
                    flip *= -1;
                }

                ball_x += flip * 10;


                Log.d(TAG, "run: " + ball_y);
                if (ball_y > screenHeight/2 - ball.getHeight()/2){
                    ball_dy *= -1;
                }

                ball_y += ball_dy;
                ball_dy += 1;


                holder.unlockCanvasAndPost(canvas);
            }
        }


        public void resume(){
            running = true;
            gameThread = new Thread(this);
            gameThread.start();
        }

        public void pause(){
            running = false;
            while(true){
                try {
                    gameThread.join();
                } catch (InterruptedException ignored){}
            }
        }

    }









}