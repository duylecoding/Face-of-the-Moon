/*    */
package com.moonbook.puzzle.entity.projectile;
/*    */ 
/*    */

import com.moonbook.puzzle.entity.Entity;
import com.moonbook.puzzle.entity.mob.BlueTotem;
import com.moonbook.puzzle.entity.mob.Mob;
import com.moonbook.puzzle.entity.mob.Mob32;
import com.moonbook.puzzle.entity.mob.Robot;
import com.moonbook.puzzle.graphics.Screen;
import com.moonbook.puzzle.graphics.Sprite;
import com.moonbook.puzzle.sounds.Sounds;

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

/*    */
/*    */ public class BasicAttack extends Projectile
/*    */ {
    /* 16 */   public static int FIRE_RATE = 30;
    /* 17 */   public static int damage = 1;

    /*    */
/*    */
    public BasicAttack(double x, double y, double dir) {
/* 20 */
        super(x, y, dir);
/* 21 */
        this.range = 150.0D;
/* 22 */
        damage = 20;
/* 23 */
        this.speed = 4.0D;
/* 24 */
        this.type = Projectile.AttType.BASIC;
/* 25 */
        this.sprite = Sprite.rotate(Sprite.basic_Att, this.angle);
/* 26 */
        this.rect = new Rectangle((int) x + 2, (int) y + 8, 7, 7);
/* 27 */
        this.nx = (this.speed * Math.cos(this.angle));
/* 28 */
        this.ny = (this.speed * Math.sin(this.angle));
/*    */
    }

    /*    */
/*    */
    public void update()
/*    */ {
/* 33 */
        this.rect.setLocation((int) this.x + 4, (int) this.y + 8);
/* 34 */
        if (this.level.tileCollision((int) (this.x + this.nx), (int) (this.y + this.ny), 7, 11, 11))
/*    */ {
/* 36 */
            remove();
/*    */
        }
/* 38 */
        move();
/*    */
    }

    /*    */
/*    */
    protected void move() {
/* 42 */
        this.x += this.nx;
/* 43 */
        this.y += this.ny;
/*    */ 
/* 45 */
        if (distance() > this.range) remove();
/*    */
    }

    /*    */
/*    */
    private double distance()
/*    */ {
/* 49 */
        double dist = 0.0D;
/* 50 */
        dist = Math.sqrt(Math.abs((this.xOrigin - this.x) * (this.xOrigin - this.x) + (this.yOrigin - this.y) * (this.yOrigin - this.y)));
/* 51 */
        return dist;
/*    */
    }

    /*    */
/*    */
    public void mobCol() {
/* 55 */
        for (int i = 0; i < this.level.getEntities().size(); i++) {
/* 56 */
            Entity e = (Entity) this.level.getEntities().get(i);
/*    */ 
/* 58 */
            if (e.getRect().intersects(getRect())) {
/* 59 */
                if ((e instanceof BlueTotem)) {
/* 60 */
                    Sounds.WTH.play();
/*    */
                }
/* 62 */
                if ((e instanceof Robot)) {
/* 63 */
                    Sounds.METAL.play();
/*    */
                }
/* 65 */
                if ((e instanceof Mob32)) {
/* 66 */
                    e.takeDmg(damage);
/*    */
                }
/* 68 */
                if ((e instanceof Mob)) {
/* 69 */
                    e.takeDmg(damage);
/*    */
                }
/*    */ 
/* 72 */
                for (int ii = 0; ii < this.level.getProjectiles().size(); ii++) {
/* 73 */
                    this.level.getProjectiles().remove(ii);
/*    */ 
/* 75 */
                    remove();
/*    */
                }
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
/* 83 */
        screen.renderProjectile((int) this.x, (int) this.y + 4, this);
/* 84 */
        mobCol();
/*    */
    }
/*    */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.entity.projectile.BasicAttack
 * JD-Core Version:    0.6.2
 */