/*    */
package com.moonbook.puzzle.entity.mob;
/*    */ 
/*    */

import com.moonbook.puzzle.Game;
import com.moonbook.puzzle.entity.projectile.BasicAttack;
import com.moonbook.puzzle.entity.projectile.Projectile;
import com.moonbook.puzzle.graphics.AnimatedSprite;
import com.moonbook.puzzle.graphics.Screen;
import com.moonbook.puzzle.graphics.Sprite;
import com.moonbook.puzzle.graphics.SpriteSheet;

import java.awt.*;

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

/*    */
/*    */ public class Minion extends Mob
/*    */ {
    /* 15 */   private AnimatedSprite down = new AnimatedSprite(SpriteSheet.pimpkid3_down, 16, 16, 3);
    /* 16 */   private AnimatedSprite up = new AnimatedSprite(SpriteSheet.pimpkid3_up, 16, 16, 3);
    /* 17 */   private AnimatedSprite left = new AnimatedSprite(SpriteSheet.pimpkid3_left, 16, 16, 3);
    /* 18 */   private AnimatedSprite right = new AnimatedSprite(SpriteSheet.pimpkid3_right, 16, 16, 3);
    /*    */
/* 20 */   private AnimatedSprite animSprite = this.down;
    /*    */
/* 22 */   private int time = 0;
    /* 23 */   private int xa = 0;
    /* 24 */   private int ya = 0;

    /*    */
/*    */
    public Minion(int x, int y) {
/* 27 */
        this.x = (x << 4);
/* 28 */
        this.y = (y << 4);
/* 29 */
        this.sprite = Sprite.pimpkid3;
/* 30 */
        this.rect = new Rectangle(x, y, 16, 16);
/* 31 */
        this.health = 80;
/*    */
    }

    /*    */
/*    */
    public void update()
/*    */ {
/* 36 */
        this.time += 1;
/* 37 */
        if (this.time % (this.random.nextInt(50) + 30) == 0) {
/* 38 */
            this.xa = (this.random.nextInt(3) - 1);
/* 39 */
            this.ya = (this.random.nextInt(3) - 1);
/* 40 */
            if (this.random.nextInt(3) == 0) {
/* 41 */
                this.xa = 0;
/* 42 */
                this.ya = 0;
/*    */
            }
/*    */
        }
/*    */ 
/* 46 */
        if (this.walking) this.animSprite.update();
        else
/* 47 */       this.animSprite.setFrame(0);
/* 48 */
        if (this.dir == Mob.Direction.UP)
/* 49 */ this.animSprite = this.up;
/* 50 */
        else if (this.dir == Mob.Direction.DOWN) {
/* 51 */
            this.animSprite = this.down;
/*    */
        }
/*    */ 
/* 54 */
        if (this.dir == Mob.Direction.LEFT)
/* 55 */ this.animSprite = this.left;
/* 56 */
        else if (this.dir == Mob.Direction.RIGHT) {
/* 57 */
            this.animSprite = this.right;
/*    */
        }
/*    */ 
/* 60 */
        if ((this.xa != 0) || (this.ya != 0)) {
/* 61 */
            move(this.xa, this.ya);
/* 62 */
            this.walking = true;
/*    */
        } else {
/* 64 */
            this.walking = false;
/*    */
        }
/* 66 */
        this.rect = new Rectangle(this.x, this.y, 16, 16);
/*    */
    }

    /*    */
/*    */
    public void mobCol()
/*    */ {
/* 71 */
        for (int i = 0; i < this.level.getProjectiles().size(); i++) {
/* 72 */
            Projectile p = (Projectile) this.level.getProjectiles().get(i);
/* 73 */
            if (p.getRect().intersects(this.rect)) {
/* 74 */
                this.health -= BasicAttack.damage;
/* 75 */
                this.level.getProjectiles().remove(i);
/*    */
            }
/* 77 */
            if (this.health <= 0) {
/* 78 */
                remove();
/* 79 */
                Game.addScore(1);
/* 80 */
                this.level.add(new Minion(15, 15));
/* 81 */
                this.health = 80;
/*    */
            }
/*    */
        }
/*    */
    }

    /*    */
/*    */
    public void render(Screen screen)
/*    */ {
/* 89 */
        this.sprite = this.animSprite.getSprite();
/*    */ 
/* 91 */
        screen.renderMob(this.x, this.y, this.sprite);
/* 92 */
        mobCol();
/*    */
    }
/*    */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.entity.mob.Minion
 * JD-Core Version:    0.6.2
 */