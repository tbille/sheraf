package com.m2dl.sheraf.dynamics.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

public class DynamicElement extends Drawable {

    protected final int screenWidth;
    protected final int screenHeight;
    private Bitmap currentFrame;
    private float xPosition;
    private float yPosition;
    private float xSpeed = 0;
    private float ySpeed = 0;
    protected Context context;

    public static float groundHeight = 600f;

    public DynamicElement(Context context, float xPosition, float yPosition) {
        this.context = context;
        this.screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        this.screenHeight = context.getResources().getDisplayMetrics().heightPixels;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public DynamicElement(Context context) {
        this.context = context;
        this.screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        this.screenHeight = context.getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        canvas.drawBitmap(currentFrame, getxPosition(), getyPosition(), paint);
    }

    public void update(long fps){
        if (fps != 0) {
            setxPosition(getxPosition() + (getxSpeed() / fps));
            setyPosition(getyPosition() + (getySpeed() / fps));
        }
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

    public float getxPosition() {
        return xPosition;
    }

    protected void setxPosition(float xPosition) {
        this.xPosition = xPosition;
    }

    protected float getxSpeed() {
        return xSpeed;
    }

    protected void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }

    public float getyPosition() {
        return yPosition;
    }

    protected void setyPosition(float yPosition) {
        this.yPosition = yPosition;
    }

    protected float getySpeed() {
        return ySpeed;
    }

    protected void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }

    protected void setCurrentFrame(Bitmap currentFrame) {
        this.currentFrame = currentFrame;
    }
}
