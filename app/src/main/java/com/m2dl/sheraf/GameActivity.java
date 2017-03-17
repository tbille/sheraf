package com.m2dl.sheraf;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.m2dl.sheraf.dynamics.elements.Player;

import com.m2dl.sheraf.enums.LightValue;
import com.m2dl.sheraf.sensors.LightSensor;
import com.m2dl.sheraf.sensors.ShakeDetector;
import com.m2dl.sheraf.sensors.SoundMeter;
import com.m2dl.sheraf.views.GameView;
import com.m2dl.sheraf.views.HeroView;

import static android.content.ContentValues.TAG;

public class GameActivity extends Activity {

    private static final int DB_SHOUT = 90;
    private static final int LOW_LIGHT = 30;
    private static final int HIGH_LIGHT = 1000;
    // gameView will be the view of the game
    // It will also hold the logic of the game
    // and respond to screen touches as well
    GameView gameView;

    final int REFRESH_SOUND_DURATION = 50;
    private SoundMeter soundMeter = new SoundMeter();
    private Handler mHandler = new Handler();

    // The following are used for the shake detection
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    private Sensor mLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize gameView and set it as the view
        gameView = new GameView(this);
        setContentView(gameView);
        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
				/*
				 * The following method, "handleShakeEvent(count):" is a stub //
				 * method you would use to setup whatever you want done once the
				 * device has been shook.
				 */
                handleShakeEvent(count);
            }
        });
        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mLight = mSensorManager
                .getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                int lightValue = (int) event.values[0];

                handleLightEvent(lightValue);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        }, mLight, SensorManager.SENSOR_DELAY_NORMAL);

        askPermission(android.Manifest.permission.RECORD_AUDIO, new String[]{android.Manifest.permission.RECORD_AUDIO}, 1);
    }

    private void askPermission(String sensor, String[] permissions, int requestCode) {
        if (ContextCompat.checkSelfPermission(this, sensor) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    permissions,
                    requestCode);
        }
    }

    private void handleLightEvent(int lightValue) {
        if(lightValue < LOW_LIGHT){
            gameView.setLight(LightValue.LOW);
        }else{
            if(lightValue > HIGH_LIGHT){
                gameView.setLight(LightValue.HIGH);
            }else{
                gameView.setLight(LightValue.MEDIUM);
            }
        }
    }

    private void handleShakeEvent(int count) {
        gameView.onShake();
    }

    @Override
    protected void onResume() {
        super.onResume();

        try{
        soundMeter.startRecorder();
        }catch(RuntimeException stopException){
            //handle cleanup here
        }
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
        // Tell the gameView resume method to execute
        gameView.resume();
    }

    // This method executes when the player quits the game
    @Override
    protected void onPause() {
        super.onPause();
        try{
            soundMeter.stopRecorder();
        }catch(RuntimeException stopException){
            //handle cleanup here
        }
        mSensorManager.unregisterListener(mShakeDetector);
        // Tell the gameView pause method to execute
        gameView.pause();
    }

    final Runnable soundUpdater = new Runnable() {

        public void run() {
            int dbValue = ((int) soundMeter.soundDb(1));
            if(dbValue >= DB_SHOUT){
                gameView.onShout(dbValue);
            }
            mHandler.postDelayed(this, REFRESH_SOUND_DURATION);
        }
    };


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("SON", "onRequestPermissionsResult: PERMISSION ACCORDEE");
                    mHandler = new Handler();
                    mHandler.postDelayed(soundUpdater, REFRESH_SOUND_DURATION);

                } else {
                    Log.d("SON", "onRequestPermissionsResult: CAMERA PERMISSION REFUSEE");
                    Toast.makeText(this, "Vous devez accepter les permissions pour continuer.", Toast.LENGTH_LONG)
                            .show();
                    finish();
                }
                return;
            }
        }
    }

}