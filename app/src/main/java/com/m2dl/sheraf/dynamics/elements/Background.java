package com.m2dl.sheraf.dynamics.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.m2dl.sheraf.R;

import static android.content.ContentValues.TAG;

public class Background extends DynamicElement {

    private Bitmap bitmapSky;

    public Background(Context context, float xSpeed) {
        setxPosition(0);
        setyPosition(0);
        setxSpeed(xSpeed);
        bitmapSky = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
        setCurrentFrame(bitmapSky);
    }

    public void update(long fps){
        Log.d(TAG, "update: " + fps);
        Log.d(TAG, "update: getxPosition " + getxPosition());
        if (fps != 0) {
            setxPosition(getxPosition() + (getxSpeed() / fps));
        }
    }
}
