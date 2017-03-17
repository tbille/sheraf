package com.m2dl.sheraf.dynamics.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.m2dl.sheraf.R;
import com.m2dl.sheraf.enums.TypePins;

/**
 * Created by Clement on 17/03/2017.
 */

public class Pins extends DynamicElement {

    public static int size = 60;
    private TypePins typePins;

    public Pins(Context context, TypePins typePins, float speed) {
        super(context);

        this.typePins = typePins;

        setxPosition(screenWidth + 200);
        setyPosition(screenHeight - groundHeight - size);
        setxSpeed(speed);

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

    public TypePins getType() {
        return typePins;
    }
}
