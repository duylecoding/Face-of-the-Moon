/*     */
package com.moonbook.puzzle.entity.mob;
/*     */ 
/*     */

import com.moonbook.puzzle.Game;
import com.moonbook.puzzle.graphics.AnimatedSprite;
import com.moonbook.puzzle.graphics.Screen;
import com.moonbook.puzzle.graphics.Sprite;
import com.moonbook.puzzle.graphics.SpriteSheet;
import com.moonbook.puzzle.level.Node;
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
/*     */ public class Robot extends Mob32
/*     */ {
    /*  17 */   private AnimatedSprite front = new AnimatedSprite(SpriteSheet.robot, 32, 32, 3);
    /*  18 */   private AnimatedSprite back = new AnimatedSprite(SpriteSheet.robot_back, 32, 32, 3);
    /*     */
/*  20 */   private AnimatedSprite animSprite = this.front;
    /*     */
/*  22 */   private double xa = 0.0D;
    /*  23 */   private double ya = 0.0D;
    /*  24 */   private List<Node> path = null;
    /*  25 */   private int time = 0;
    /*  26 */   private int px = 0;
    private int py = 0;

    /*     */
/*     */
    public Robot(int x, int y) {
/*  29 */
        this.x = (x << 4);
/*  30 */
        this.y = (y << 4);
/*  31 */
        this.sprite = Sprite.blueTotem;
/*  32 */
        this.rect = new Rectangle(x, y, 32, 32);
/*  33 */
        this.health = 200;
/*  34 */
        this.speed = 1.0D;
/*  35 */
        this.passable = false;
/*     */
    }

    /*     */
/*     */
    private void move() {
/*  39 */
        this.xa = 0.0D;
/*  40 */
        this.ya = 0.0D;
/*     */ 
/*  48 */
        int px = this.level.getPlayerAt(0).getX();
/*  49 */
        int py = this.level.getPlayerAt(0).getY();
/*  50 */
        Vector2i start = new Vector2i(getX() >> 4, getY() >> 4);
/*  51 */
        Vector2i destination = new Vector2i(px >> 4, py >> 4);
/*  52 */
        if (this.time % 15 == 0) this.path = this.level.findPath(start, destination);
/*  53 */
        if (this.time > 1000) this.time = 0;
/*     */ 
/*  55 */
        if ((this.path != null) &&
/*  56 */       (this.path.size() > 0)) {
/*  57 */
            Vector2i vec = ((Node) this.path.get(this.path.size() - 1)).tile;
/*  58 */
            if (this.x < vec.getX() << 4) this.xa += this.speed;
/*  59 */
            if (this.x > vec.getX() << 4) this.xa -= this.speed;
/*  60 */
            if (this.y < vec.getY() << 4) this.ya += this.speed;
/*  61 */
            if (this.y > vec.getY() << 4) this.ya -= this.speed;
/*     */ 
/*     */
        }
/*     */ 
/*  66 */
        if (this.time % 2 == 0)
/*  67 */ if ((this.xa != 0.0D) || (this.ya != 0.0D)) {
/*  68 */
            move(this.xa, this.ya);
/*  69 */
            this.walking = true;
/*     */
        } else {
/*  71 */
            this.walking = false;
/*     */
        }
/*     */
    }

    /*     */
/*     */
    public void update()
/*     */ {
/*  78 */
        this.time += 1;
/*  79 */
        if (this.health <= 0) die();
/*  80 */
        move();
/*  81 */
        if (this.walking) this.animSprite.update();
        else
/*  82 */       this.animSprite.setFrame(0);
/*  83 */
        if (this.dir == Mob32.Direction.UP)
/*  84 */ this.animSprite = this.back;
/*  85 */
        else if (this.dir == Mob32.Direction.DOWN) {
/*  86 */
            this.animSprite = this.front;
/*     */
        }
/*  88 */
        if (this.dir == Mob32.Direction.LEFT)
/*  89 */ this.animSprite = this.front;
/*  90 */
        else if (this.dir == Mob32.Direction.RIGHT) {
/*  91 */
            this.animSprite = this.front;
/*     */
        }
/*  93 */
        this.rect.setLocation(this.x + 5, this.y + 5);
/*     */
    }

    /*     */
/*     */
    private void die()
/*     */ {
/*  99 */
        remove();
/* 100 */
        this.level.add(new Robot(16, 15));
/* 101 */
        this.level.add(new Robot(6, 20));
/* 102 */
        Game.addScore(5);
/*     */
    }

    /*     */
/*     */
    public void render(Screen screen)
/*     */ {
/* 108 */
        this.sprite = this.animSprite.getSprite();
/* 109 */
        screen.renderMob(this.x, this.y, this, 32);
/* 110 */
        screen.drawRect(this.x, this.y + 2, this.health / 7, 1, 16711680, true);
/* 111 */
        screen.drawRect(this.x, this.y + 1, 29, 3, 16711680, true);
/*     */
    }
/*     */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.entity.mob.Robot
 * JD-Core Version:    0.6.2
 */