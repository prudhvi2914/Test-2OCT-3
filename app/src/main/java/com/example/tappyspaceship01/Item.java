package com.example.tappyspaceship01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Item {

    // PROPERTIES:
    // Image
    // Hitbox
    private Bitmap image;
    private Bitmap image2;
    private Bitmap image3;
    private Rect hitbox;

    private int xPosition;
    private int yPosition;

    //Implemnet randomly appereing items
    List<Item> images = new ArrayList<>();

    public Item(Context context, int x, int y,Bitmap image , Rect hitbox) {
        // 1. set up the initial position of the Enemy
        this.xPosition = x;
        this.yPosition = y;
this.image = image;
        // 2. Set the default image - all enemies have same image

//
//images.add(R.drawable.poop64);
//int images[]={(image,image2,image3)};

        // 3. Set the default hitbox - all enemies have same hitbox
        this.hitbox = new Rect(
                this.xPosition,
                this.yPosition,
                this.xPosition + this.image.getWidth(),
                this.yPosition + this.image.getHeight()
        );
    }

    // Getter and setters
    // Autogenerate this by doing Right Click --> Generate --> Getter&Setter


    //-----------------------------

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Rect getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rect hitbox) {
        this.hitbox = hitbox;
    }



    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }
    public void updateHitbox() {
        this.hitbox.left = this.xPosition;
        this.hitbox.top = this.yPosition;
        this.hitbox.right = this.xPosition + this.image.getWidth();
        this.hitbox.bottom = this.yPosition + this.image.getHeight();
    }

}


//

