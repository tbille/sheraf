package com.m2dl.sheraf.dynamics.elements;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.m2dl.sheraf.ObstacleSize;

import static android.content.ContentValues.TAG;

public class Obstacle extends DynamicElement {


    private float width;
    private float height;
    private Context context;

    public Obstacle(Context context, ObstacleSize size, float speed) {
        this.context = context;
        setxPosition(context.getResources().getDisplayMetrics().widthPixels + 200);
        Log.d(TAG, "Obstacle: " + getxPosition());
        setyPosition(groundHeight);

        setSize(size);

        setxSpeed(speed);
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        canvas.drawRect(getxPosition(), getyPosition(), getxPosition() + width, getyPosition() + height, paint);
    }

    public void update(long fps){
        if (fps != 0) {
            setxPosition(getxPosition() + (getxSpeed() / fps));
        }
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setSize(ObstacleSize size) {
        switch (size) {
            case SMALL:
                this.width = 100;
                this.height = 100;
                break;
            case MEDIUM:
                this.width = 100;
                this.height = 200;
                break;
            case BIG:
                this.width = 100;
                this.height = 300;
                break;
        }
    }
}
