package com.example.lab1lightsensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.renderscript.Sampler.Value;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager sensor;
    private TextView text;
    private TextView text_name;
    private TextView text_power;
    private TextView text_max;

    private TextView text_manu;
    private TextView text_num;
    private TextView text_resolution;
    private TextView text_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensor = (SensorManager) getSystemService(SENSOR_SERVICE);
        text = findViewById(R.id.textView1);
        text_name = findViewById(R.id.textView3);
        text_power = findViewById(R.id.textView4);
        text_max = findViewById(R.id.textView2);
        text_manu = findViewById(R.id.tv1);
        text_num = findViewById(R.id.tv2);
        text_resolution = findViewById(R.id.tv3);
        text_type = findViewById(R.id.tv4);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        sensor.unregisterListener(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        sensor.registerListener(this, sensor.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_GAME);
        Sensor lightSensor = sensor.getDefaultSensor(Sensor.TYPE_LIGHT);
        text_name.setText("Light Sensor Name: ");
        text_name.append(lightSensor.getName());
        float max_value = lightSensor.getMaximumRange();
        text_max.setText("Maximum Measuring Range: ");
        text_max.append(String.valueOf(max_value));

        text_manu.setText("Equipment Supplier: ");
        text_manu.append(lightSensor.getVendor());
        text_num.setText("Device version: ");
        text_num.append(String.valueOf(lightSensor.getVersion()));
        text_resolution.setText("Sensor Accuracy: ");
        text_resolution.append(String.valueOf(lightSensor.getResolution()));
        text_type.setText("Sensor Type: ");
        text_type.append(String.valueOf(lightSensor.getType()));

        super.onResume();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        sensor.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO Auto-generated method stub
        float[] values = event.values;
        text.setText("Light Intensity: ");
        text.append(String.valueOf(values[0]));
        Sensor lightSensor = sensor.getDefaultSensor(Sensor.TYPE_LIGHT);
        float power = lightSensor.getPower();
        text_power.setText("Power Consumption (mA):");
        text_power.append(String.valueOf(power));
    }
}
