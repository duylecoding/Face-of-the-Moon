/*    */
package com.moonbook.puzzle.entity.mob;
/*    */ 
/*    */

import com.moonbook.puzzle.Game;
import com.moonbook.puzzle.graphics.AnimatedSprite;
import com.moonbook.puzzle.graphics.Screen;
import com.moonbook.puzzle.graphics.Sprite;
import com.moonbook.puzzle.graphics.SpriteSheet;
import com.moonbook.puzzle.level.Node;
import com.moonbook.puzzle.util.Vector2i;

import java.awt.*;
import java.util.List;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ public class Chaser extends Mob
/*    */ {
    /* 16 */   private AnimatedSprite down = new AnimatedSprite(SpriteSheet.pimpkid3_down, 16, 16, 3);
    /* 17 */   private AnimatedSprite up = new AnimatedSprite(SpriteSheet.pimpkid3_up, 16, 16, 3);
    /* 18 */   private AnimatedSprite left = new AnimatedSprite(SpriteSheet.pimpkid3_left, 16, 16, 3);
    /* 19 */   private AnimatedSprite right = new AnimatedSprite(SpriteSheet.pimpkid3_right, 16, 16, 3);
    /*    */
/* 21 */   private AnimatedSprite animSprite = this.down;
    /*    */
/* 23 */   private double xa = 0.0D;
    /* 24 */   private double ya = 0.0D;
    /* 25 */   private double speed = 0.8D;
    /* 26 */   private List<Node> path = null;
    /* 27 */   private int time = 0;

    /*    */
/* 29 */
    public Chaser(int x, int y) {
        this.x = (x << 4);
/* 30 */
        this.y = (y << 4);
/* 31 */
        this.sprite = Sprite.pimpkid3;
/* 32 */
        this.rect = new Rectangle(x, y, 16, 16);
/* 33 */
        this.health = 100;
    }

    /*    */
/*    */
    private void move()
/*    */ {
/* 37 */
        this.xa = 0.0D;
/* 38 */
        this.ya = 0.0D;
/*    */ 
/* 46 */
        int px = this.level.getPlayerAt(0).getX();
/* 47 */
        int py = this.level.getPlayerAt(0).getY();
/*    */ 
/* 49 */
        Vector2i start = new Vector2i(getX() >> 4, getY() >> 4);
/* 50 */
        Vector2i destination = new Vector2i(px >> 4, py >> 4);
/* 51 */
        if (this.time % 15 == 0) this.path = this.level.findPath(start, destination);
/*    */ 
/* 53 */
        if ((this.path != null) &&
/* 54 */       (this.path.size() > 0)) {
/* 55 */
            Vector2i vec = ((Node) this.path.get(this.path.size() - 1)).tile;
/* 56 */
            if (this.x < vec.getX() << 4) this.xa += this.speed;
/* 57 */
            if (this.x > vec.getX() << 4) this.xa -= this.speed;
/* 58 */
            if (this.y < vec.getY() << 4) this.ya += this.speed;
/* 59 */
            if (this.y > vec.getY() << 4) this.ya -= this.speed;
/*    */ 
/*    */
        }
/*    */ 
/* 63 */
        if ((this.xa != 0.0D) || (this.ya != 0.0D)) {
/* 64 */
            move(this.xa, this.ya);
/* 65 */
            this.walking = true;
/*    */
        } else {
/* 67 */
            this.walking = false;
/*    */
        }
/*    */
    }

    /*    */
/*    */
    public void die()
/*    */ {
/* 73 */
        if (this.health <= 0) {
/* 74 */
            remove();
/* 75 */
            Game.addScore(1);
/* 76 */
            this.level.add(new Chaser(15, 15));
/* 77 */
            this.health = 100;
/*    */
        }
/*    */
    }

    /*    */
/*    */
    public void render(Screen screen)
/*    */ {
/* 84 */
        this.sprite = this.animSprite.getSprite();
/* 85 */
        screen.renderMob(this.x, this.y, this, 16);
/* 86 */
        die();
/*    */
    }

    /*    */
/*    */
    public void update()
/*    */ {
/* 91 */
        move();
/* 92 */
        this.time += 1;
/*    */
    }
/*    */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.entity.mob.Chaser
 * JD-Core Version:    0.6.2
 */