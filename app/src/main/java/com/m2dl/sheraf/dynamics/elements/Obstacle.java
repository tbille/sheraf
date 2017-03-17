package com.m2dl.sheraf.dynamics.elements;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.m2dl.sheraf.ObstacleSize;

public class Obstacle extends DynamicElement {

    private float width;
    private float height;

    public Obstacle(Context context, ObstacleSize size, float speed) {
        super(context);

        initSize(size);
        
        setxPosition(screenWidth + 200);
        setyPosition(groundHeight - getHeight());

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

    public void initSize(ObstacleSize size) {
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
