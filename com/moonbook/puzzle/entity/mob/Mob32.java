/*     */
package com.moonbook.puzzle.entity.mob;
/*     */ 
/*     */

import com.moonbook.puzzle.entity.Entity;
import com.moonbook.puzzle.entity.projectile.BasicAttack;
import com.moonbook.puzzle.entity.projectile.Projectile;
import com.moonbook.puzzle.graphics.Screen;

import java.awt.*;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */

/*     */
/*     */ public abstract class Mob32 extends Entity
/*     */ {
    /*  14 */   protected boolean walking = false;
    /*  15 */   protected boolean moving = false;
    /*     */   protected Rectangle rect;
    /*     */   protected Direction dir;

    /*     */
/*     */
    public void move(double xa, double ya)
/*     */ {
/*  25 */
        if ((xa != 0.0D) && (ya != 0.0D)) {
/*  26 */
            move(xa, 0.0D);
/*  27 */
            move(0.0D, ya);
/*  28 */
            return;
/*     */
        }
/*  30 */
        if (xa > 0.0D) this.dir = Direction.RIGHT;
/*  31 */
        if (xa < 0.0D) this.dir = Direction.LEFT;
/*  32 */
        if (ya > 0.0D) this.dir = Direction.DOWN;
/*  33 */
        if (ya < 0.0D) this.dir = Direction.UP;
/*     */ 
/*  35 */
        while (xa != 0.0D) {
/*  36 */
            if (Math.abs(xa) > 1.0D) {
/*  37 */
                if (!collision(abs(xa), ya)) {
/*  38 */
                    this.x += abs(xa);
/*     */
                }
/*  40 */
                xa -= abs(xa);
/*     */
            } else {
/*  42 */
                if (!collision(abs(xa), ya)) {
/*  43 */
                    this.x = ((int) (this.x + xa));
/*     */
                }
/*  45 */
                xa = 0.0D;
/*     */
            }
/*     */
        }
/*  48 */
        while (ya != 0.0D)
/*  49 */ if (Math.abs(ya) > 1.0D) {
/*  50 */
            if (!collision(xa, abs(ya))) {
/*  51 */
                this.y += abs(ya);
/*     */
            }
/*  53 */
            ya -= abs(ya);
/*     */
        } else {
/*  55 */
            if (!collision(xa, abs(ya))) {
/*  56 */
                this.y = ((int) (this.y + ya));
/*     */
            }
/*  58 */
            ya = 0.0D;
/*     */
        }
/*     */
    }

    /*     */
/*     */
    private int abs(double value)
/*     */ {
/*  65 */
        if (value < 0.0D) return -1;
/*  66 */
        return 1;
/*     */
    }

    /*     */
/*     */
    protected boolean minBlock() {
/*  70 */
        boolean solid = false;
/*  71 */
        Entity p = this.level.getPlayerAt(0);
/*  72 */
        if (p.getRect().intersects(this.rect)) solid = true;
/*     */ 
/*  74 */
        return solid;
/*     */
    }

    /*     */
/*     */
    public abstract void update();

    /*     */
/*     */
    public abstract void render(Screen paramScreen);

    /*     */
/*     */
    protected void shoot(double x, double y, double dir) {
/*  82 */
        Projectile p = new BasicAttack(x, y, dir);
/*  83 */
        this.level.add(p);
/*     */
    }

    /*     */
/*     */
    public Rectangle getRect() {
/*  87 */
        return this.rect;
/*     */
    }

    /*     */
/*     */
    private boolean collision(double xa, double ya) {
/*  91 */
        boolean solid = false;
/*  92 */
        for (int c = 0; c < 4; c++) {
/*  93 */
            double xt = (this.x + xa + c % 2) / 16.0D;
/*  94 */
            double yt = (this.y + ya + c / 2) / 16.0D;
/*  95 */
            int ix = (int) Math.ceil(xt);
/*  96 */
            int iy = (int) Math.ceil(yt);
/*  97 */
            if (c % 2 == 0) ix = (int) Math.floor(xt);
/*  98 */
            if (c / 2 == 0) iy = (int) Math.floor(yt);
/*  99 */
            if (this.level.getTile(ix, iy).solid()) solid = true;
/*     */
        }
/* 101 */
        return solid;
/*     */
    }

    /*     */
/*     */   protected static enum Direction
/*     */ {
        /*  19 */     UP, DOWN, LEFT, RIGHT;
/*     */
    }
/*     */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.entity.mob.Mob32
 * JD-Core Version:    0.6.2
 */