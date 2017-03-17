package com.m2dl.sheraf.dynamics.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.m2dl.sheraf.R;

public class TextDisplay extends DynamicElement {
    public TextDisplay(Context context, float xPosition, float yPosition) {
        super(context, xPosition, yPosition);
        Bitmap textSprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.touch_to_start);
        setCurrentFrame(textSprite);
    }


}
