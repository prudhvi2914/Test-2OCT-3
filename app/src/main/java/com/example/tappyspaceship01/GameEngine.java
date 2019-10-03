package com.example.tappyspaceship01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameEngine extends SurfaceView implements Runnable {

    // Android debug variables
    final static String TAG="DINO-RAINBOWS";

    // screen size
    int screenHeight;
    int screenWidth;

    // game state
    boolean gameIsRunning;

    // threading
    Thread gameThread;


    // drawing variables
    SurfaceHolder holder;
    Canvas canvas;
    Paint paintbrush;
//
int playerXPosition;
    int playerYPosition;

//Implemnet randomly appereing items
List<Item> items = new ArrayList<>();



    // -----------------------------------
    // GAME SPECIFIC VARIABLES
    // -----------------------------------

    // ----------------------------
    // ## SPRITES
    // ----------------------------

    // represent the TOP LEFT CORNER OF THE GRAPHIC

    Player player;
   Item enemy1;
    Item enemy2;
    Item like1;
    Item like2;


    // ----------------------------
    // ## GAME STATS
    // ----------------------------

    int lives = 3;
    int score= 0;


    public GameEngine(Context context, int w, int h) {
        super(context);
//Implemnet randomly appereing items
        List<Item> items = new ArrayList<>();

        this.holder = this.getHolder();
        this.paintbrush = new Paint();

        this.screenWidth = w;
        this.screenHeight = h;


        this.printScreenInfo();

        // @TODO: Add your sprites


        // put the initial starting position of your player and enemies
        this.player = new Player(getContext(), 2000, 300);
        this.enemy1 = new Item(getContext(), 80, 80);
        this.enemy2 = new Item(getContext(), 80, 300);
        this.like1 = new Item(getContext(), 80, 600);
        this.like2 = new Item(getContext(), 80, 900);

        items.add(enemy1);
        items.add(enemy2);
        items.add(like1);
        items.add(like2);
        Collections.shuffle(items);


    }


    private void printScreenInfo() {

        Log.d(TAG, "Screen (w, h) = " + this.screenWidth + "," + this.screenHeight);
    }

    private void spawnPlayer() {
        //@TODO: Start the player at the left side of screen
    }
    private void spawnEnemyShips() {
        Random random = new Random();

        //@TODO: Place the enemies in a random location

    }

    // ------------------------------
    // GAME STATE FUNCTIONS (run, stop, start)
    // ------------------------------
    @Override
    public void run() {
        while (gameIsRunning == true) {
            this.updatePositions();
            this.redrawSprites();
            this.setFPS();
        }
    }


    public void pauseGame() {
        gameIsRunning = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            // Error
        }
    }

    public void startGame() {
        gameIsRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }


    // ------------------------------
    // GAME ENGINE FUNCTIONS
    // - update, draw, setFPS
    // ------------------------------

    int numLoops = 0;


    public void updatePositions() {

        // UPDATE BACKGROUND POSITION
        // 1. Move the background




        numLoops = numLoops + 1;

        // @TODO: Update position of player based on mouse tap
        if (this.fingerAction == "mousedown") {
            // if mousedown, then move player up
            // Make the UP movement > than down movement - this will
            // make it look like the player is moving up alot
            this.player.setyPosition(-100);
            player.updateHitbox();
        }
        else if (this.fingerAction == "mouseup") {
            // if mouseup, then move player down
            this.player.setyPosition(+100);
            player.updateHitbox();
        }

        // MAKE ENEMY MOVE
        // - enemy moves left forever
        // - when enemy touches LEFT wall, respawn on RIGHT SIDE
        this.enemy1.setxPosition(this.enemy1.getxPosition()+25);

        // MOVE THE HITBOX (recalcluate the position of the hitbox)
        this.enemy1.updateHitbox();

        if (this.enemy1.getxPosition() <= 0) {
            // restart the enemy in the starting position
            this.enemy1.setxPosition(1300);
            this.enemy1.setyPosition(120);
            this.enemy1.updateHitbox();
        }




        // MAKE ENEMY2 MOVE
        // MAKE ENEMY MOVE
        // - enemy moves left forever
        // - when enemy touches LEFT wall, respawn on RIGHT SIDE
        this.enemy2.setxPosition(this.enemy2.getxPosition()+10);

        // MOVE THE HITBOX (recalcluate the position of the hitbox)
        this.enemy2.updateHitbox();

        if (this.enemy2.getxPosition() <= 0) {
            // restart the enemy in the starting position
            this.enemy2.setxPosition(1500);
            this.enemy2.setyPosition(500);

            this.enemy2.updateHitbox();
        }

        this.like1.setxPosition(this.like1.getxPosition()+25);

        // MOVE THE HITBOX (recalcluate the position of the hitbox)
        this.like1.updateHitbox();

        if (this.like1.getxPosition() <= 0) {
            // restart the enemy in the starting position
            this.like1.setxPosition(1300);
            this.like1.setyPosition(700);
            this.like1.updateHitbox();
        }

        this.like2.setxPosition(this.like2.getxPosition()+25);

        // MOVE THE HITBOX (recalcluate the position of the hitbox)
        this.like2.updateHitbox();

        if (this.like2.getxPosition() <= 0) {
            // restart the enemy in the starting position
            this.like2.setxPosition(1300);
            this.like2.setyPosition(120);
            this.like2.updateHitbox();
        }



        // @TODO:  Check collisions between enemy and player
        if (this.player.getHitbox().intersect(this.enemy1.getHitbox()) == true) {
            // the enemy and player are colliding
            Log.d(TAG, "++++++ENEMY AND PLAYER COLLIDING!");

            // @TODO: What do you want to do next?

            // RESTART THE PLAYER IN ORIGINAL POSITION
            // -------
            // 1. Restart the player
            // 2. Restart the player's hitbox
            this.player.setxPosition(2000);
            this.player.setyPosition(900);
            this.player.updateHitbox();

            // decrease the lives
            lives = lives - 1;

        }


        // @TODO:  Check collisions between enemy2 and player
        if (this.player.getHitbox().intersect(this.enemy2.getHitbox()) == true) {
            // the enemy and player are colliding
            Log.d(TAG, "++++++ENEMY 2 AND PLAYER COLLIDING!");

            // @TODO: What do you want to do next?

            // RESTART THE PLAYER IN ORIGINAL POSITION
            // -------
            // 1. Restart the player
            // 2. Restart the player's hitbox
            this.player.setxPosition(2000);
            this.player.setyPosition(900);
            this.player.updateHitbox();

            // decrease the lives
            lives = lives - 1;

        }

        if (this.player.getHitbox().intersect(this.like1.getHitbox()) == true) {
            // the enemy and player are colliding
            Log.d(TAG, "++++++ENEMY AND PLAYER COLLIDING!");

            // @TODO: What do you want to do next?

            // RESTART THE PLAYER IN ORIGINAL POSITION
            // -------
            // 1. Restart the player
            // 2. Restart the player's hitbox


            // decrease the lives
            score = score + 1;

        }
        if (this.player.getHitbox().intersect(this.like2.getHitbox()) == true) {
            // the enemy and player are colliding
            Log.d(TAG, "++++++ENEMY AND PLAYER COLLIDING!");

            // @TODO: What do you want to do next?

            // RESTART THE PLAYER IN ORIGINAL POSITION
            // -------
            // 1. Restart the player
            // 2. Restart the player's hitbox


            // decrease the lives
            score = score + 1;

        }





    }

    public void redrawSprites() {
        if (this.holder.getSurface().isValid()) {
            this.canvas = this.holder.lockCanvas();

            //----------------



            // configure the drawing tools
            this.canvas.drawColor(Color.argb(255,255,255,255));
            paintbrush.setColor(Color.WHITE);


            // DRAW THE PLAYER HITBOX
            // ------------------------
            // 1. change the paintbrush settings so we can see the hitbox
            paintbrush.setColor(Color.BLACK);
            paintbrush.setStyle(Paint.Style.STROKE);
            paintbrush.setStrokeWidth(5);



//drawing a line
            canvas.drawLine(10,300,3000,300,paintbrush);
            canvas.drawLine(10,600,3000,600,paintbrush);
            canvas.drawLine(10,900,3000,900,paintbrush);
            canvas.drawLine(10,1200,3000,1200,paintbrush);

            // draw player graphic on screen
            canvas.drawBitmap(player.getImage(), player.getxPosition(), player.getyPosition(), paintbrush);
            // draw the player's hitbox
            canvas.drawRect(player.getHitbox(), paintbrush);

            // draw the enemy graphic on the screen
            canvas.drawBitmap(enemy1.getImage(), enemy1.getxPosition(), enemy1.getyPosition(), paintbrush);
            // 2. draw the enemy's hitbox
            canvas.drawRect(enemy1.getHitbox(), paintbrush);


            // draw enemy 2 on the screen
            // draw the enemy graphic on the screen
            canvas.drawBitmap(enemy2.getImage(), enemy2.getxPosition(), enemy2.getyPosition(), paintbrush);
            // 2. draw the enemy's hitbox
            canvas.drawRect(enemy2.getHitbox(), paintbrush);

            canvas.drawBitmap(like1.getImage(), like1.getxPosition(), like1.getyPosition(), paintbrush);
            // 2. draw the enemy's hitbox
            canvas.drawRect(like1.getHitbox(), paintbrush);
            canvas.drawBitmap(like2.getImage(), like2.getxPosition(), like2.getyPosition(), paintbrush);
            // 2. draw the enemy's hitbox
            canvas.drawRect(like2.getHitbox(), paintbrush);

            // -----------------------------
            paintbrush.setColor(Color.BLACK);
            paintbrush.setTextSize(60);
            canvas.drawText("Lives remaining: " + lives,
                    100,
                    100,
                    paintbrush
            );

            paintbrush.setColor(Color.GREEN);
            paintbrush.setTextSize(80);
            canvas.drawText("SCORE : " + score,
                    100,
                    300,
                    paintbrush
            );



            //----------------
            this.holder.unlockCanvasAndPost(canvas);
        }
    }

    public void setFPS() {
        try {
            gameThread.sleep(120);
        }
        catch (Exception e) {

        }
    }

    // ------------------------------
    // USER INPUT FUNCTIONS
    // ------------------------------


    String fingerAction = "";

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int userAction = event.getActionMasked();
        //@TODO: What should happen when person touches the screen?
        // Answer - PRESS DOWN ON SCREEN --> PLAYER MOVES UP
        // RELEASE FINGER --> PLAYER MOVES DOWN

        float fingerXPosition = event.getX();
        float fingerYPosition = event.getY();

        int middleOfScreen = this.screenHeight / 2;
        if (fingerYPosition <= middleOfScreen) {

            fingerAction = "mousedown";
        }
        else if (fingerYPosition >= middleOfScreen) {
            //Log.d(TAG, "Person lifted finger");
            // User has released finger, so move player down
            fingerAction = "mouseup";
        }

        return true;
    }
}