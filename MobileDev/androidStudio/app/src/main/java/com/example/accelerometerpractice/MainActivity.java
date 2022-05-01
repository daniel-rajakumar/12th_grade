package com.example.accelerometerpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements SensorEventListener2 {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometerSensor, sensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onFlushCompleted(Sensor sensor) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float _x = sensorEvent.values[0];
        float _y = sensorEvent.values[1];
        float _z = sensorEvent.values[2];
        float _brightness = brightness * 100;


        x.setText(String.format("%.2f", _x));
        y.setText(String.format("%.2f", _y));
        z.setText(String.format("%.2f", _z));


        brightness -= Math.signum(_x) * 0.1f;

        if (brightness < 0) {
            brightness = 0;
        } else if (brightness > 1f){
            brightness = 1;
        }

        brightnessParams.screenBrightness = brightness;
        getWindow().setAttributes(brightnessParams);


        bright.setText(String.format("%.2f%%", brightness * 100));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}