/*     */
package com.moonbook.puzzle.entity.mob;
/*     */ 
/*     */

import com.moonbook.puzzle.Game;
import com.moonbook.puzzle.entity.Entity;
import com.moonbook.puzzle.graphics.AnimatedSprite;
import com.moonbook.puzzle.graphics.Screen;
import com.moonbook.puzzle.graphics.Sprite;
import com.moonbook.puzzle.graphics.SpriteSheet;
import com.moonbook.puzzle.util.Vector2i;

import java.awt.*;
import java.util.List;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */

/*     */
/*     */ public class Shooter extends Mob
/*     */ {
    /*  16 */   private AnimatedSprite down = new AnimatedSprite(SpriteSheet.pimpkid3_down, 16, 16, 3);
    /*  17 */   private AnimatedSprite up = new AnimatedSprite(SpriteSheet.pimpkid3_up, 16, 16, 3);
    /*  18 */   private AnimatedSprite left = new AnimatedSprite(SpriteSheet.pimpkid3_left, 16, 16, 3);
    /*  19 */   private AnimatedSprite right = new AnimatedSprite(SpriteSheet.pimpkid3_right, 16, 16, 3);
    /*     */
/*  21 */   private AnimatedSprite animSprite = this.down;
    /*     */
/*  23 */   private int time = 0;
    /*  24 */   private int xa = 0;
    /*  25 */   private int ya = 0;
    /*     */   private Entity rand;

    /*     */
/*     */
    public Shooter(int x, int y)
/*     */ {
/*  30 */
        this.x = (x << 4);
/*  31 */
        this.y = (y << 4);
/*  32 */
        this.sprite = Sprite.pimpkid3;
/*  33 */
        this.rect = new Rectangle(x, y, 16, 16);
/*  34 */
        this.health = 300;
/*     */
    }

    /*     */
/*     */
    public void update()
/*     */ {
/*  39 */
        this.time += 1;
/*  40 */
        if (this.time % (this.random.nextInt(50) + 30) == 0) {
/*  41 */
            this.xa = (this.random.nextInt(3) - 1);
/*  42 */
            this.ya = (this.random.nextInt(3) - 1);
/*  43 */
            if (this.random.nextInt(3) == 0) {
/*  44 */
                this.xa = 0;
/*  45 */
                this.ya = 0;
/*     */
            }
/*     */
        }
/*     */ 
/*  49 */
        if (this.walking) this.animSprite.update();
        else
/*  50 */       this.animSprite.setFrame(0);
/*  51 */
        if (this.dir == Mob.Direction.UP)
/*  52 */ this.animSprite = this.up;
/*  53 */
        else if (this.dir == Mob.Direction.DOWN) {
/*  54 */
            this.animSprite = this.down;
/*     */
        }
/*     */ 
/*  57 */
        if (this.dir == Mob.Direction.LEFT)
/*  58 */ this.animSprite = this.left;
/*  59 */
        else if (this.dir == Mob.Direction.RIGHT) {
/*  60 */
            this.animSprite = this.right;
/*     */
        }
/*     */ 
/*  63 */
        if ((this.xa != 0) || (this.ya != 0)) {
/*  64 */
            move(this.xa, this.ya);
/*  65 */
            this.walking = true;
/*     */
        } else {
/*  67 */
            this.walking = false;
/*     */
        }
/*     */ 
/*  70 */
        shootRandom();
/*  71 */
        this.rect = new Rectangle(this.x, this.y, 16, 16);
/*     */
    }

    /*     */
/*     */
    private void shootRandom() {
/*  75 */
        if (this.time % 60 == 0) {
/*  76 */
            List entities = this.level.getEntities(this, 500);
/*  77 */
            entities.add(this.level.getClientPlayer());
/*  78 */
            int index = this.random.nextInt(entities.size());
/*  79 */
            this.rand = ((Entity) entities.get(index));
/*     */
        }
/*     */ 
/*  82 */
        if (this.rand != null) {
/*  83 */
            double dx = this.rand.getX() - this.x;
/*  84 */
            double dy = this.rand.getY() - this.y;
/*  85 */
            double dir = Math.atan2(dy, dx);
/*  86 */
            if (this.time % 20 == 0) shoot(this.x, this.y, dir);
/*     */
        }
/*     */
    }

    /*     */
/*     */
    private void shootClosest()
/*     */ {
/*  91 */
        List entities = this.level.getEntities(this, 500);
/*  92 */
        entities.add(this.level.getClientPlayer());
/*     */ 
/*  94 */
        double min = 0.0D;
/*  95 */
        Entity closest = null;
/*     */ 
/*  97 */
        for (int i = 0; i < entities.size(); i++) {
/*  98 */
            Entity e = (Entity) entities.get(i);
/*  99 */
            double distance = Vector2i.getDistance(new Vector2i(this.x, this.y), new Vector2i(e.getX(), e.getY()));
/* 100 */
            if ((i == 0) || (distance < min)) {
/* 101 */
                min = distance;
/* 102 */
                closest = e;
/*     */
            }
/*     */
        }
/*     */ 
/* 106 */
        if (closest != null) {
/* 107 */
            double dx = closest.getX() - this.x;
/* 108 */
            double dy = closest.getY() - this.y;
/* 109 */
            double dir = Math.atan2(dy, dx);
/* 110 */
            if (this.time % 20 == 0) shoot(this.x, this.y, dir);
/*     */
        }
/*     */
    }

    /*     */
/*     */
    public void die()
/*     */ {
/* 115 */
        if (this.health <= 0) {
/* 116 */
            remove();
/* 117 */
            this.level.add(new Shooter(15, 15));
/* 118 */
            Game.addScore(2);
/* 119 */
            this.health = 300;
/*     */
        }
/*     */
    }

    /*     */
/*     */
    public void render(Screen screen)
/*     */ {
/* 126 */
        this.sprite = this.animSprite.getSprite();
/* 127 */
        screen.renderMob(this.x, this.y, this, 16);
/* 128 */
        die();
/*     */
    }
/*     */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.entity.mob.Shooter
 * JD-Core Version:    0.6.2
 */