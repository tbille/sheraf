package com.m2dl.sheraf.dynamics.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import com.m2dl.sheraf.R;

public class Background extends DynamicElement {

    private final float groundSpeed;
    private final float backgroundSpeed;
    private Bitmap bitmapGround2;
    private Bitmap bitmapGround1;
    private Bitmap bitmapSky1;
    private Bitmap bitmapSky2;

    private float background1xPosition;
    private float background2xPosition;

    private float ground1xPosition;
    private float ground2xPosition;

    public Background(Context context, float groundSpeed) {
        super(context, 0, 0);
        this.backgroundSpeed = -100;
        this.groundSpeed = groundSpeed;

        setBackground1xPosition(-screenWidth / 2);
        setBackground2xPosition(screenWidth / 2);
        setGround1xPosition(-screenWidth / 2);
        setGround2xPosition(screenWidth / 2);

        bitmapSky1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
        bitmapSky1 = Bitmap.createScaledBitmap(bitmapSky1, screenWidth + 40, screenHeight, false);
        bitmapSky2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
        bitmapSky2 = Bitmap.createScaledBitmap(bitmapSky1, screenWidth + 40, screenHeight, false);


        bitmapGround1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ground);
        bitmapGround1 = Bitmap.createScaledBitmap(bitmapGround1, screenWidth + 40, (int) groundHeight, false);
        bitmapGround2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ground);
        bitmapGround2 = Bitmap.createScaledBitmap(bitmapGround2, screenWidth + 40, (int) groundHeight, false);

        Matrix m = new Matrix();
        m.preScale(-1, 1);
        bitmapSky2 = Bitmap.createBitmap(bitmapSky2, 0, 0, bitmapSky2.getWidth(), bitmapSky2.getHeight(), m, false);
        bitmapSky2.setDensity(DisplayMetrics.DENSITY_DEFAULT);

        bitmapGround2 = Bitmap.createBitmap(bitmapGround2, 0, 0, bitmapGround2.getWidth(), bitmapGround2.getHeight(), m, false);
        bitmapGround2.setDensity(DisplayMetrics.DENSITY_DEFAULT);
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        canvas.drawBitmap(bitmapSky1, getBackground1xPosition(), 0, paint);
        canvas.drawBitmap(bitmapSky2, getBackground2xPosition(), 0, paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawBitmap(bitmapGround1, getGround1xPosition(), screenHeight - groundHeight, paint);
        canvas.drawBitmap(bitmapGround2, getGround2xPosition(), screenHeight - groundHeight, paint);
        //canvas.drawRect(0, screenHeight - groundHeight, screenWidth, screenHeight, paint);
    }

    public void update(long fps){
       if (fps != 0) {
           if (getBackground1xPosition() < -1 * screenWidth) {
               setBackground1xPosition(screenWidth);
           } else {
               setBackground1xPosition(getBackground1xPosition() + (backgroundSpeed / fps));
           }
           if (getBackground2xPosition() < -1 * screenWidth) {
               setBackground2xPosition(screenWidth);
           } else {
               setBackground2xPosition(getBackground2xPosition() + (backgroundSpeed / fps));
           }
           if (getGround1xPosition() < -1 * screenWidth) {
               setGround1xPosition(screenWidth);
           } else {
               setGround1xPosition(getGround1xPosition() + (groundSpeed / fps));
           }
           if (getGround2xPosition() < -1 * screenWidth) {
               setGround2xPosition(screenWidth);
           } else {
               setGround2xPosition(getGround2xPosition() + (groundSpeed / fps));
           }

       }
    }

    public float getBackground2xPosition() {
        return background2xPosition;
    }

    public void setBackground2xPosition(float background2xPosition) {
        this.background2xPosition = background2xPosition;
    }

    public float getGround1xPosition() {
        return ground1xPosition;
    }

    public void setGround1xPosition(float ground1xPosition) {
        this.ground1xPosition = ground1xPosition;
    }

    public float getGround2xPosition() {
        return ground2xPosition;
    }

    public void setGround2xPosition(float ground2xPosition) {
        this.ground2xPosition = ground2xPosition;
    }

    public float getBackground1xPosition() {
        return background1xPosition;
    }

    public void setBackground1xPosition(float background1xPosition) {
        this.background1xPosition = background1xPosition;
    }
}
