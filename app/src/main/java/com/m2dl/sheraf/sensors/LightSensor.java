package com.m2dl.sheraf.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.FloatMath;

public class LightSensor implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mLight;

    private OnLightChangedListener mListener;

    public void setOnLightChangedListener(OnLightChangedListener listener) {
        this.mListener = listener;
    }

    public interface OnLightChangedListener {
        public void onLightChanged(int lightValue);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // ignore
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (mListener != null) {
                int lightValue = (int) event.values[0];
                mListener.onLightChanged(lightValue);
            }
        }
    }
