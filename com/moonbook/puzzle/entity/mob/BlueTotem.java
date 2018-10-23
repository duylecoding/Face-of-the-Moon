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
import com.moonbook.puzzle.sounds.Sounds;
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

/*     */
/*     */ public class BlueTotem extends Mob32
/*     */ {
    /*     */
/*  36 */   public static int count = 0;
    /*  17 */   private AnimatedSprite being = new AnimatedSprite(SpriteSheet.blueTotem, 32, 32, 3);
    /*     */
/*  19 */   private AnimatedSprite animSprite = this.being;
    /*     */
/*  21 */   private double xa = 0.0D;
    /*  22 */   private double ya = 0.0D;
    /*  23 */   private List<Node> path = null;
    /*  24 */   private int time = 0;
    /*  25 */   private int px = 0;
    private int py = 0;

    /*     */
/*     */
    public BlueTotem(int x, int y)
/*     */ {
/*  28 */
        this.x = (x << 4);
/*  29 */
        this.y = (y << 4);
/*  30 */
        this.sprite = Sprite.blueTotem;
/*  31 */
        this.rect = new Rectangle(x, y, 20, 29);
/*  32 */
        this.health = 150;
/*  33 */
        this.speed = 1.0D;
/*  34 */
        this.passable = false;
/*     */
    }

    /*     */
/*     */
    private void move()
/*     */ {
/*  39 */
        this.xa = 0.0D;
/*  40 */
        this.ya = 0.0D;
/*     */ 
/*  48 */
        int px = this.level.getPlayerAt(0).getX();
/*  49 */
        int py = this.level.getPlayerAt(0).getY();
/*     */ 
/*  51 */
        Vector2i start = new Vector2i(getX() >> 4, getY() >> 4);
/*  52 */
        Vector2i destination = new Vector2i(px >> 4, py >> 4);
/*  53 */
        if (this.time % 30 == 0) this.path = this.level.findPath(start, destination);
/*  54 */
        if (this.time > 1000) this.time = 0;
/*     */ 
/*  56 */
        if ((this.path != null) &&
/*  57 */       (this.path.size() > 0)) {
/*  58 */
            Vector2i vec = ((Node) this.path.get(this.path.size() - 1)).tile;
/*  59 */
            if (this.x < vec.getX() << 4) this.xa += this.speed;
/*  60 */
            if (this.x > vec.getX() << 4) this.xa -= this.speed;
/*  61 */
            if (this.y < vec.getY() << 4) this.ya += this.speed;
/*  62 */
            if (this.y > vec.getY() << 4) this.ya -= this.speed;
/*     */ 
/*     */
        }
/*     */ 
/*  66 */
        if ((this.xa != 0.0D) || (this.ya != 0.0D)) {
/*  67 */
            move(this.xa, this.ya);
/*  68 */
            this.walking = true;
/*     */
        } else {
/*  70 */
            this.walking = false;
/*     */
        }
/*     */
    }

    /*     */
/*     */
    public void update()
/*     */ {
/*  77 */
        this.time += 1;
/*  78 */
        if (this.health <= 0) die();
/*  79 */
        move();
/*  80 */
        if (this.walking) this.animSprite.update();
        else
/*  81 */       this.animSprite.setFrame(0);
/*  82 */
        if (this.dir == Mob32.Direction.UP)
/*  83 */ this.animSprite = this.being;
/*  84 */
        else if (this.dir == Mob32.Direction.DOWN) {
/*  85 */
            this.animSprite = this.being;
/*     */
        }
/*  87 */
        if (this.dir == Mob32.Direction.LEFT)
/*  88 */ this.animSprite = this.being;
/*  89 */
        else if (this.dir == Mob32.Direction.RIGHT) {
/*  90 */
            this.animSprite = this.being;
/*     */
        }
/*     */ 
/*  93 */
        if (this.time % 2 == 0) this.rect.setLocation(this.x + 5, this.y);
/*     */
    }

    /*     */
/*     */
    private void die()
/*     */ {
/*  99 */
        remove();
/* 100 */
        this.level.add(new BlueTotem(20, 15));
/* 101 */
        this.level.add(new BlueTotem(7, 20));
/* 102 */
        Game.addScore(3);
/* 103 */
        Sounds.WHINE.play();
/* 104 */
        Player.stuns += 1;
/*     */
    }

    /*     */
/*     */
    public void render(Screen screen)
/*     */ {
/* 110 */
        count += 1;
/* 111 */
        this.sprite = this.animSprite.getSprite();
/* 112 */
        screen.renderMob(this.x, this.y, this, 32);
/* 113 */
        screen.drawRect(this.x, this.y + 2, this.health / 5, 1, 16711680, true);
/* 114 */
        screen.drawRect(this.x, this.y + 1, 30, 3, 16711680, true);
/*     */
    }
/*     */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.entity.mob.BlueTotem
 * JD-Core Version:    0.6.2
 */