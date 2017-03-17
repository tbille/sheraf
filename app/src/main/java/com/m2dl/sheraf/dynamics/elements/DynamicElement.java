package com.m2dl.sheraf.dynamics.elements;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

public abstract class DynamicElement extends Drawable {

    private Bitmap currentFrame;
    private float xPosition;
    private float xSpeed;
    private float yPosition;
    private float ySpeed;

    public static float groundHeight = 500f;

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        canvas.drawBitmap(currentFrame, getxPosition(), getyPosition(), paint);
    }

    public void update(long fps){

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
