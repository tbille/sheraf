package com.m2dl.sheraf.dynamics.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.m2dl.sheraf.R;

import static android.content.ContentValues.TAG;

public class Background extends DynamicElement {

    private Bitmap bitmapSky;

    public Background(Context context, float xSpeed) {
        setxPosition(0);
        setyPosition(0);
        setxSpeed(xSpeed);
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inMutable = true;
        bitmapSky = BitmapFactory.decodeResource(context.getResources(), R.drawable.background, opt);

        setCurrentFrame(bitmapSky);
    }

    public void update(long fps){
       /* if (fps != 0) {
            setxPosition(getxPosition() + (getxSpeed() / fps));
        }*/
    }
}
