/*    */
package com.moonbook.puzzle.entity;
/*    */ 
/*    */

import com.moonbook.puzzle.graphics.Screen;
import com.moonbook.puzzle.graphics.Sprite;
import com.moonbook.puzzle.level.Level;

import java.awt.*;
import java.util.Random;

/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ public class Entity
/*    */ {
    /* 16 */   protected final Random random = new Random();
    /*    */   protected int x;
    /*    */   protected int y;
    /*    */   protected Sprite sprite;
    /*    */   protected Level level;
    /* 17 */   protected Rectangle rect = new Rectangle();
    /*    */   protected int health;
    /*    */   protected double speed;
    /*    */   protected boolean passable;
    /* 14 */   private boolean removed = false;

    /*    */
/*    */
    public Entity()
/*    */ {
/*    */
    }

    /*    */
/*    */
    public Entity(int x, int y, Sprite sprite)
/*    */ {
/* 26 */
        this.x = x;
/* 27 */
        this.y = y;
/* 28 */
        this.sprite = sprite;
/*    */
    }

    /*    */
/*    */
    public void update()
/*    */ {
/*    */
    }

    /*    */
/*    */
    public void render(Screen screen) {
/* 36 */
        if (this.sprite != null) screen.renderSprite(this.x, this.y, this.sprite, true);
/*    */
    }

    /*    */
/*    */
    public void remove()
/*    */ {
/* 40 */
        this.removed = true;
/*    */
    }

    /*    */
/*    */
    public int getX() {
/* 44 */
        return this.x;
/*    */
    }

    /*    */
/*    */
    public int getY() {
/* 48 */
        return this.y;
/*    */
    }

    /*    */
/*    */
    public Rectangle getRect() {
/* 52 */
        return this.rect;
/*    */
    }

    /*    */
/*    */
    public Sprite getSprite() {
/* 56 */
        return this.sprite;
/*    */
    }

    /*    */
/*    */
    public boolean isRemoved()
/*    */ {
/* 61 */
        return this.removed;
/*    */
    }

    /*    */
/*    */
    public void init(Level level) {
/* 65 */
        this.level = level;
/*    */
    }

    /*    */
/*    */
    public void takeDmg(int dam) {
/* 69 */
        this.health -= dam;
/* 70 */
        if (this.health < 0) this.health = 0;
/*    */
    }

    /*    */
/*    */
    public void slow(double slow)
/*    */ {
/* 74 */
        this.speed = 0.0D;
/*    */
    }

    /*    */
/*    */
    public double getSpeed() {
/* 82 */
        return this.speed;
/*    */
    }

    /*    */
/*    */
    public void setSpeed(double speed) {
/* 78 */
        this.speed = speed;
/*    */
    }

    /*    */
/*    */
    public int getHealth() {
/* 86 */
        return this.health;
/*    */
    }
/*    */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.entity.Entity
 * JD-Core Version:    0.6.2
 */