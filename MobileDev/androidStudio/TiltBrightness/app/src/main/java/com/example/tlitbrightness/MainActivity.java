package com.example.tlitbrightness;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener2 {

    ProgressBar progressBar;
    float brightness = -1;
    WindowManager.LayoutParams brightnessParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.progressBar = findViewById(R.id.progressBar);

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometerSensor  = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);

        brightnessParams = getWindow().getAttributes();
    }

    @Override
    public void onFlushCompleted(Sensor sensor) { }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x = sensorEvent.values[0];

        brightness -= Math.signum(x) * 0.01f; // -1, 0, or 1.
        brightness  = Math.max(0, Math.min(1, brightness)); // keep the range between 0.0 - 1.0

        brightnessParams.screenBrightness = brightness;
        getWindow().setAttributes(brightnessParams);

        progressBar.setProgress((int) (brightness * 100));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) { }
}