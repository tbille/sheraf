package com.m2dl.sheraf.dynamics.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.util.Log;

import com.m2dl.sheraf.R;

import static android.content.ContentValues.TAG;

public class Background extends DynamicElement {

    private Bitmap bitmapSky1;
    private Bitmap bitmapSky2;


    private float xPosition2;
    private float yPosition2;

    public Background(Context context) {
        super(context, 0, 0);
        setxSpeed(-100);

        setxPosition(-screenWidth /2);
        setxPosition2(screenWidth /2);
        setyPosition2(0);

        bitmapSky1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
        bitmapSky1 = Bitmap.createScaledBitmap(bitmapSky1, screenWidth, screenHeight, false);
        bitmapSky2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
        bitmapSky2 = Bitmap.createScaledBitmap(bitmapSky1, screenWidth, screenHeight, false);

        Matrix m = new Matrix();
        m.preScale(-1, 1);
        bitmapSky2 = Bitmap.createBitmap(bitmapSky2, 0, 0, bitmapSky2.getWidth(), bitmapSky2.getHeight(), m, false);
        bitmapSky2.setDensity(DisplayMetrics.DENSITY_DEFAULT);

    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        canvas.drawBitmap(bitmapSky1, getxPosition(), getyPosition(), paint);
        canvas.drawBitmap(bitmapSky2, getxPosition2(), getyPosition2(), paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.LTGRAY);
        canvas.drawRect(0, screenHeight - groundHeight, screenWidth, screenHeight, paint);
    }

    public void update(long fps){
       if (fps != 0) {
           if (getxPosition() < -1 * screenWidth) {
               setxPosition(screenWidth);
           } else {
               setxPosition(getxPosition() + (getxSpeed() / fps));
           }
           if (getxPosition2() < -1 * screenWidth) {
               setxPosition2(screenWidth);
           } else {
               setxPosition2(getxPosition2() + (getxSpeed() / fps));
           }

       }
    }

    public float getxPosition2() {
        return xPosition2;
    }

    public void setxPosition2(float xPosition2) {
        this.xPosition2 = xPosition2;
    }

    public float getyPosition2() {
        return yPosition2;
    }

    public void setyPosition2(float yPosition2) {
        this.yPosition2 = yPosition2;
    }
}
