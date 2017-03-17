package com.m2dl.sheraf.dynamics.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.m2dl.sheraf.R;
import com.m2dl.sheraf.enums.TypePins;

import java.util.Random;

public class Pins extends DynamicElement {

    public static int size = 60;
    private TypePins typePins;

    public Pins(Context context, TypePins typePins, float baseSpeed) {
        super(context);

        this.typePins = typePins;

        int rand = getRandomInt(250, 400);
        int randSpeed = getRandomInt(200, 500);

        setxPosition(screenWidth + 200);
        setyPosition(screenHeight - groundHeight - rand - size);
        setxSpeed(baseSpeed + randSpeed);

        Bitmap icon = null;

        switch (this.typePins) {
            case SHERAF:
                icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.sheraf);
                break;
            case OUICHE:
                icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.pins);
                break;
        }
        icon = Bitmap.createScaledBitmap(icon, size, size, false);
        setCurrentFrame(icon);
    }

    public void update(long fps){
        if (fps != 0) {
            setxPosition(getxPosition() + (getxSpeed() / fps));
        }
    }

    private int getRandomInt(int min, int max) {
        Random rn = new Random();
        return rn.nextInt(max - min + 1) + min;
    }

    public TypePins getType() {
        return typePins;
    }
}
