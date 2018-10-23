/*    */
package com.moonbook.puzzle.entity.projectile;
/*    */ 
/*    */

import com.moonbook.puzzle.entity.Entity;
import com.moonbook.puzzle.entity.mob.BlueTotem;
import com.moonbook.puzzle.entity.mob.Mob;
import com.moonbook.puzzle.entity.mob.Mob32;
import com.moonbook.puzzle.entity.spawner.ParticleSpawner;
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
/*    */ public class SlowAtt extends Projectile
/*    */ {
    /* 16 */   public static int FIRE_RATE = 30;
    /* 17 */   public static int damage = 10;
    /* 18 */   private int time = 0;

    /*    */
/*    */
    public SlowAtt(double x, double y, double dir) {
/* 21 */
        super(x, y, dir);
/* 22 */
        this.range = 150.0D;
/* 23 */
        damage = 20;
/* 24 */
        this.speed = 4.0D;
/* 25 */
        this.type = Projectile.AttType.FROST;
/* 26 */
        this.sprite = Sprite.rotate(Sprite.slow_Att, this.angle);
/* 27 */
        this.rect = new Rectangle((int) x + 2, (int) y + 8, 7, 7);
/* 28 */
        this.nx = (this.speed * Math.cos(this.angle));
/* 29 */
        this.ny = (this.speed * Math.sin(this.angle));
/*    */
    }

    /*    */
/*    */
    public void update()
/*    */ {
/* 34 */
        this.time += 1;
/* 35 */
        if (this.time % 3 == 0) this.sprite = Sprite.rotate(this.sprite, 0.1570796326794897D);
/* 36 */
        this.rect.setLocation((int) this.x + 4, (int) this.y + 8);
/* 37 */
        if (this.level.tileCollision((int) (this.x + this.nx), (int) (this.y + this.ny), 7, 11, 11)) {
/* 38 */
            this.level.add(new ParticleSpawner((int) this.x, (int) this.y, 44, 5, this.level, Projectile.AttType.FROST));
/* 39 */
            remove();
/*    */
        }
/* 41 */
        move();
/*    */
    }

    /*    */
/*    */
    protected void move() {
/* 45 */
        this.x += this.nx;
/* 46 */
        this.y += this.ny;
/*    */ 
/* 48 */
        if (distance() > this.range) remove();
/*    */
    }

    /*    */
/*    */
    private double distance()
/*    */ {
/* 52 */
        double dist = 0.0D;
/* 53 */
        dist = Math.sqrt(Math.abs((this.xOrigin - this.x) * (this.xOrigin - this.x) + (this.yOrigin - this.y) * (this.yOrigin - this.y)));
/* 54 */
        return dist;
/*    */
    }

    /*    */
/*    */
    public void mobCol() {
/* 58 */
        for (int i = 0; i < this.level.getEntities().size(); i++) {
/* 59 */
            Entity e = (Entity) this.level.getEntities().get(i);
/*    */ 
/* 61 */
            if (e.getRect().intersects(getRect())) {
/* 62 */
                if ((e instanceof BlueTotem)) {
/* 63 */
                    Sounds.WTH.play();
/*    */
                }
/* 65 */
                if ((e instanceof Mob32)) {
/* 66 */
                    e.takeDmg(damage);
/* 67 */
                    e.slow(0.05D);
/*    */
                }
/* 69 */
                if ((e instanceof Mob)) {
/* 70 */
                    e.takeDmg(damage);
/* 71 */
                    e.slow(0.05D);
/*    */
                }
/*    */ 
/* 74 */
                for (int ii = 0; ii < this.level.getProjectiles().size(); ii++) {
/* 75 */
                    this.level.getProjectiles().remove(ii);
/* 76 */
                    this.level.add(new ParticleSpawner((int) this.x, (int) this.y, 44, 5, this.level, Projectile.AttType.FROST));
/* 77 */
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
/* 85 */
        screen.renderProjectile((int) this.x, (int) this.y + 4, this);
/* 86 */
        mobCol();
/*    */
    }
/*    */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.entity.projectile.SlowAtt
 * JD-Core Version:    0.6.2
 */