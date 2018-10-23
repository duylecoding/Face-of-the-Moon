/*     */
package com.moonbook.puzzle.entity.mob;
/*     */ 
/*     */

import com.moonbook.puzzle.entity.Entity;
import com.moonbook.puzzle.entity.projectile.BasicAttack;
import com.moonbook.puzzle.entity.projectile.Projectile;
import com.moonbook.puzzle.entity.projectile.SlowAtt;
import com.moonbook.puzzle.graphics.Screen;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */

/*     */
/*     */ public abstract class Mob extends Entity
/*     */ {
    /*  13 */   protected boolean walking = false;
    /*  14 */   protected boolean moving = false;
    /*     */   protected Direction dir;

    /*     */
/*     */
    public void move(double xa, double ya)
/*     */ {
/*  23 */
        if ((xa != 0.0D) && (ya != 0.0D)) {
/*  24 */
            move(xa, 0.0D);
/*  25 */
            move(0.0D, ya);
/*  26 */
            return;
/*     */
        }
/*     */ 
/*  29 */
        if (xa > 0.0D) this.dir = Direction.RIGHT;
/*  30 */
        if (xa < 0.0D) this.dir = Direction.LEFT;
/*  31 */
        if (ya > 0.0D) this.dir = Direction.DOWN;
/*  32 */
        if (ya < 0.0D) this.dir = Direction.UP;
/*     */ 
/*  34 */
        if (((this instanceof Player)) &&
/*  35 */       (minBlock())) {
/*  36 */
            xa += xa * 1.0D;
/*  37 */
            ya += ya * 1.0D;
/*     */
        }
/*     */ 
/*  41 */
        while (xa != 0.0D) {
/*  42 */
            if (Math.abs(xa) > 1.0D) {
/*  43 */
                if (!collision(abs(xa), ya)) {
/*  44 */
                    this.x += abs(xa);
/*     */
                }
/*  46 */
                xa -= abs(xa);
/*     */
            } else {
/*  48 */
                if (!collision(abs(xa), ya)) {
/*  49 */
                    this.x = ((int) (this.x + xa));
/*     */
                }
/*  51 */
                xa = 0.0D;
/*     */
            }
/*     */
        }
/*  54 */
        while (ya != 0.0D)
/*  55 */ if (Math.abs(ya) > 1.0D) {
/*  56 */
            if (!collision(xa, abs(ya))) {
/*  57 */
                this.y += abs(ya);
/*     */
            }
/*  59 */
            ya -= abs(ya);
/*     */
        } else {
/*  61 */
            if (!collision(xa, abs(ya))) {
/*  62 */
                this.y = ((int) (this.y + ya));
/*     */
            }
/*  64 */
            ya = 0.0D;
/*     */
        }
/*     */
    }

    /*     */
/*     */
    protected boolean minBlock()
/*     */ {
/*  71 */
        boolean solid = false;
/*  72 */
        for (int i = 0; i < this.level.getEntities().size(); i++) {
/*  73 */
            Entity e = (Entity) this.level.getEntities().get(i);
/*  74 */
            if (e.getRect().intersects(this.rect)) solid = true;
/*     */
        }
/*  76 */
        return solid;
/*     */
    }

    /*     */
/*     */
    private int abs(double value) {
/*  80 */
        if (value < 0.0D) return -1;
/*  81 */
        return 1;
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
/*  89 */
        Projectile p = new BasicAttack(x, y, dir);
/*  90 */
        this.level.add(p);
/*     */
    }

    /*     */
/*     */
    protected void shootSlow(double x, double y, double dir) {
/*  94 */
        Projectile p = new SlowAtt(x, y, dir);
/*  95 */
        this.level.add(p);
/*     */
    }

    /*     */
/*     */
    private boolean collision(double xa, double ya) {
/*  99 */
        boolean solid = false;
/* 100 */
        for (int c = 0; c < 4; c++) {
/* 101 */
            double xt = (this.x + xa + c % 2 - 1.0D) / 16.0D;
/* 102 */
            double yt = (this.y + ya + c / 2) / 16.0D;
/* 103 */
            int ix = (int) Math.ceil(xt);
/* 104 */
            int iy = (int) Math.ceil(yt);
/* 105 */
            if (c % 2 == 0) ix = (int) Math.floor(xt);
/* 106 */
            if (c / 2 == 0) iy = (int) Math.floor(yt);
/* 107 */
            if (this.level.getTile(ix, iy).solid()) solid = true;
/*     */
        }
/* 109 */
        return solid;
/*     */
    }

    /*     */
/*     */   protected static enum Direction
/*     */ {
        /*  17 */     UP, DOWN, LEFT, RIGHT;
/*     */
    }
/*     */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.entity.mob.Mob
 * JD-Core Version:    0.6.2
 */