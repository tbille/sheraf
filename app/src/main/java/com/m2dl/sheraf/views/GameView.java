package com.m2dl.sheraf.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.m2dl.sheraf.ObstacleSize;
import com.m2dl.sheraf.TypePins;
import com.m2dl.sheraf.dynamics.elements.Background;
import com.m2dl.sheraf.dynamics.elements.Player;
import com.m2dl.sheraf.enums.LightValue;
import com.m2dl.sheraf.dynamics.elements.Obstacle;
import com.m2dl.sheraf.dynamics.elements.Pins;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import static android.content.ContentValues.TAG;

public class GameView extends SurfaceView implements  Runnable {

    private final int screenWidth;
    private final int screenHeight;
    private Canvas canvas;
    private long fps;
    private long timeThisFrame;
    private Thread gameThread = null;
    private SurfaceHolder ourHolder;
    private volatile boolean playing;
    private float gameSpeed = -100;
    private Player player;

    private Background background;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Pins> pins;
    private LightValue lightValue;

    public GameView(Context context) {
        super(context);
        // Initialize ourHolder and paint objects
        ourHolder = getHolder();

        background = new Background(context, gameSpeed + 50);
        obstacles = new ArrayList<>();
        pins = new ArrayList<>();

        pins.add(new Pins(context, TypePins.SHERAF, gameSpeed));

        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        screenHeight = context.getResources().getDisplayMetrics().heightPixels;
        // Set our boolean to true - game on!
        playing = true;
    }

    @Override
    public void run() {
        while (playing) {

            // Capture the current time in milliseconds in startFrameTime
            long startFrameTime = System.currentTimeMillis();

            // Update the frame
            update();

            // Draw the frame
            draw();

            // Calculate the fps this frame
            // We can then use the result to
            // time animations and more.
            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame > 0) {
                fps = 1000 / timeThisFrame;
            }

        }
    }

    private void draw() {
        if (ourHolder.getSurface().isValid()) {
            // Lock the canvas ready to draw
            canvas = ourHolder.lockCanvas();

            // Draw bob at bobXPosition, 200 pixels
            background.draw(canvas);

            for (Obstacle obstacle: obstacles) {
                obstacle.draw(canvas);
            }
            for (Pins pin: pins) {
                pin.draw(canvas);
            }
            // Draw everything to the screen
            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void update() {
        Log.d(TAG, "update: " + obstacles.size());
        background.update(fps);
        if (fps != 0) {
            generateRandomObstacle();
            for (Iterator<Obstacle> iterator = obstacles.iterator(); iterator.hasNext(); ) {
                Obstacle obstacle = iterator.next();
                obstacle.update(fps);
                if (obstacle.getxPosition() + obstacle.getWidth() < 0) {
                    iterator.remove();
                }
            }
        }

        for (Iterator<Pins> iterator = pins.iterator(); iterator.hasNext(); ) {
            Pins pin = iterator.next();
            pin.update(fps);
            if (pin.getxPosition() + Pins.size < 0) {
                iterator.remove();
            }
        }
    }

    private int nbUpdate = 0;
    private int nbUpdateBerforeGeneration = 1000;
    private void generateRandomObstacle() {
        Random rn = new Random();
        if (nbUpdate == nbUpdateBerforeGeneration) {
            if (rn.nextBoolean()) {
                int randomSize = getRandomInt(1, 3);

                switch (randomSize) {
                    case 1:
                        Log.d(TAG, "generateRandomObstacle: NEW SMALL");
                        obstacles.add(new Obstacle(getContext(), ObstacleSize.SMALL, backgroundSpeed));
                        break;
                    case 2:
                        Log.d(TAG, "generateRandomObstacle: NEW MEDIUM");
                        obstacles.add(new Obstacle(getContext(), ObstacleSize.MEDIUM, backgroundSpeed));
                        break;
                    case 3:
                        Log.d(TAG, "generateRandomObstacle: NEW BIG");
                        obstacles.add(new Obstacle(getContext(), ObstacleSize.BIG, backgroundSpeed));
                        break;
                }

                setRandomNbUpdateBerforeGenerationDependingOnDifficulty();
                nbUpdate = 0;
            }
        } else {
            nbUpdate++;
        }
    }

    private void setRandomNbUpdateBerforeGenerationDependingOnDifficulty() {
        //TODO write algo pour générer random en fonction de la difficulté
        nbUpdateBerforeGeneration = getRandomInt(75, 100);
    }
    private int getRandomInt(int min, int max) {
        Random rn = new Random();
        return rn.nextInt(max - min + 1) + min;
    }

    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }

    }

    // If SimpleGameEngine Activity is started then
    // start our thread.
    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

            // Player has touched the screen
            case MotionEvent.ACTION_DOWN:

                break;

            // Player has removed finger from screen
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    public void onShake() {
        //player.tryEatOuiche();
    }

    public void onShout() {
        //player.setNervousBreakdown(true);
    }

    public void setLight(LightValue value) {
        this.lightValue = value;
    }
}
