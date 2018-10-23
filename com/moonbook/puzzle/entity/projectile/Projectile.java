/*    */
package com.moonbook.puzzle.entity.projectile;
/*    */ 
/*    */

import com.moonbook.puzzle.entity.Entity;
import com.moonbook.puzzle.graphics.Sprite;

import java.awt.*;
import java.util.Random;

/*    */
/*    */
/*    */

/*    */
/*    */ public abstract class Projectile extends Entity
/*    */ {
    /*    */   protected final double xOrigin;
    /*    */   protected final double yOrigin;
    /* 24 */   protected final Random random = new Random();
    /*    */   protected double angle;
    /*    */   protected Sprite sprite;
    /*    */   protected double x;
    /*    */   protected double y;
    /*    */   protected double nx;
    /*    */   protected double ny;
    /*    */   protected double distance;
    /*    */   protected double speed;
    /*    */   protected double range;
    /*    */   protected double damage;
    /*    */   protected AttType type;
    /* 25 */   protected Rectangle rect = new Rectangle();

    /*    */
/*    */
    public Projectile(double x, double y, double dir) {
/* 28 */
        this.xOrigin = x;
/* 29 */
        this.yOrigin = y;
/* 30 */
        this.angle = dir;
/*    */ 
/* 32 */
        this.x = x;
/* 33 */
        this.y = y;
/*    */
    }

    /*    */
/*    */
    public Rectangle getRect() {
/* 37 */
        return this.rect;
/*    */
    }

    /*    */
/*    */
    public Sprite getSprite() {
/* 41 */
        return this.sprite;
/*    */
    }

    /*    */
/*    */
    public int getSpriteSize() {
/* 45 */
        return this.sprite.SIZE;
/*    */
    }

    /*    */
/*    */
    protected void move()
/*    */ {
/*    */
    }

    /*    */
/*    */   public static enum AttType
/*    */ {
        /* 12 */     BASIC, FROST;
/*    */
    }
/*    */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.entity.projectile.Projectile
 * JD-Core Version:    0.6.2
 */