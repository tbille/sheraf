package com.m2dl.sheraf.views;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.view.SurfaceView;

import com.m2dl.sheraf.R;

/**
 * Created by Clement on 17/03/2017.
 */

public class HeroView extends Drawable {

    Bitmap sprite;
    float positionX;
    float xSpeed = 150;

    public HeroView(SurfaceView view){
        sprite = BitmapFactory.decodeResource(view.getResources(), R.drawable.bob);
        positionX = 10;
    }
    @Override
    public void draw(Canvas canvas) {
        // Draw bob at bobXPosition, 200 pixels
        Paint paint = new Paint();
        canvas.drawBitmap(sprite, positionX, 200, paint);
    }

    public void update(long fps){
        positionX = positionX + (xSpeed / fps);
    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }
}
