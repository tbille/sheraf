package com.m2dl.sheraf.views;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.m2dl.sheraf.ObstacleSize;
import com.m2dl.sheraf.R;
import com.m2dl.sheraf.dynamics.elements.Background;
import com.m2dl.sheraf.dynamics.elements.Obstacle;

import java.util.ArrayList;
import java.util.Iterator;

public class GameView extends SurfaceView implements  Runnable {

    private final int screenWidth;
    private final int screenHeight;
    private Canvas canvas;
    private long fps;
    private long timeThisFrame;
    private Thread gameThread = null;
    private SurfaceHolder ourHolder;
    private volatile boolean playing;
    private float backgroundSpeed = -100;

    private Background background;
    private ArrayList<Obstacle> obstacles;

    public GameView(Context context) {
        super(context);
        // Initialize ourHolder and paint objects
        ourHolder = getHolder();

        background = new Background(context, backgroundSpeed + 50);
        obstacles = new ArrayList<>();

        obstacles.add(new Obstacle(context, ObstacleSize.SMALL, backgroundSpeed));

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

            // Draw everything to the screen
            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void update() {
        background.update(fps);
        for (Iterator<Obstacle> iterator = obstacles.iterator(); iterator.hasNext(); ) {
            Obstacle obstacle = iterator.next();
            obstacle.update(fps);
            if (obstacle.getxPosition() + obstacle.getWidth() < 0) {
                iterator.remove();
            }
        }
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
}
