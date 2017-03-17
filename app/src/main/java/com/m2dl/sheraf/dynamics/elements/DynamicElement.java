package com.m2dl.sheraf.dynamics.elements;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

/**
 * Created by Clement on 17/03/2017.
 */

public abstract class DynamicElement extends Drawable {

    private Bitmap currentFrame;
    float xPosition;
    float xSpeed;
    float yPosition;
    float ySpeed;

    @Override
    public void draw(Canvas canvas) {

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



    protected float getxPosition() {
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

    protected float getyPosition() {
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
