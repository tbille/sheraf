package com.m2dl.sheraf.dynamics.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.m2dl.sheraf.R;

public class Background extends DynamicElement {

    private Bitmap bitmapSky;

    public Background(Context context, float xSpeed) {
        super(context, 0, 0);
        setxSpeed(xSpeed);

        bitmapSky = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
        bitmapSky = Bitmap.createScaledBitmap(bitmapSky, screenWidth, screenHeight, false);

        setCurrentFrame(bitmapSky);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.LTGRAY);
        canvas.drawRect(0, groundHeight, screenWidth, screenHeight, paint);
    }

    public void update(long fps){
       /* if (fps != 0) {
            setxPosition(getxPosition() + (getxSpeed() / fps));
        }*/
    }
}
