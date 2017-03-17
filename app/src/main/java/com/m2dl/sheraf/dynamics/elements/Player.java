package com.m2dl.sheraf.dynamics.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

import com.m2dl.sheraf.R;
import com.m2dl.sheraf.enums.TypePins;

public class Player extends DynamicElement {

    //TODO modify when pins & shake detector are ready

    private boolean isLimiteNervousBreakdown;
    private boolean isJumping;
    private Bitmap jumpingSprite;
    private  Bitmap standingSprite;
    private boolean isOuicheEaten;
    private int pinsNumber;
    private int ouicheTimer = 1000;
    private float ground;
    private boolean nervousBreakdownReady;

    public Player(Context context, float xPosition, float yPosition) {
        super(context, xPosition, yPosition);
        jumpingSprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.player_jumping);
        standingSprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.player_standing);
        ground = screenHeight - groundHeight - standingSprite.getHeight();
        setyPosition(ground);
        isJumping = false;
        isOuicheEaten = false;
        isLimiteNervousBreakdown = false;
        pinsNumber = 0;
        setCurrentFrame(standingSprite);
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        if(pinsNumber == 5){
            drawOuicheReady();
        }
    }

    private void drawOuicheReady() {
    }

    public void tryEatOuiche(){
        if(pinsNumber >= 5){
            isOuicheEaten = true;
            pinsNumber = 0;
        }
    }

    public void jump(){
        if(!isJumping){
            if(isOuicheEaten){
                setySpeed(-5000);
                setCurrentFrame(jumpingSprite);
                isJumping = true;
            }else{
                setySpeed(-3000);
                setCurrentFrame(jumpingSprite);
                isJumping = true;
            }
        }
    }

    public void update(long fps){
        if(isJumping){
            setyPosition(getyPosition() + getySpeed() /fps);
            setySpeed(getySpeed() + 200f);
            if(getyPosition() >= ground){
                setyPosition(ground);
                setySpeed(0);
                isJumping = false;
                setCurrentFrame(standingSprite);
            }
        }
        if (isOuicheEaten){
            ouicheTimer--;
            if (ouicheTimer <= 0){
                isOuicheEaten = false;
                ouicheTimer = 1000;
            }
        }
    }

    public void pickupPins(Pins pins) {
        switch (pins.getType()) {
            case SHERAF:
                setNervousBreakdown(true);
                break;
            case OUICHE:
                pinsNumber++;
                break;
        }
    }

    public void setNervousBreakdown(boolean nervousBreakdownState) {
        isLimiteNervousBreakdown = nervousBreakdownState;
    }

    public boolean isLimiteNervousBreakdown(){
        return isLimiteNervousBreakdown;
    }
}
