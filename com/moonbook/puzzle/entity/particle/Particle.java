/*    */
package com.moonbook.puzzle.entity.particle;
/*    */ 
/*    */

import com.moonbook.puzzle.entity.Entity;
import com.moonbook.puzzle.graphics.Screen;
import com.moonbook.puzzle.graphics.Sprite;

/*    */
/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ public class Particle extends Entity
/*    */ {
    /*    */   protected double xx;
    /*    */   protected double yy;
    /*    */   protected double zz;
    /*    */   protected double xa;
    /*    */   protected double ya;
    /*    */   protected double za;
    /*    */   private Sprite sprite;
    /*    */   private int life;
    /* 13 */   private int time = 0;

    /*    */
/*    */
    public Particle(int x, int y, int life)
/*    */ {
/* 19 */
        this.x = x;
/* 20 */
        this.y = y;
/* 21 */
        this.xx = x;
/* 22 */
        this.yy = y;
/* 23 */
        this.life = (life + (this.random.nextInt(23) - 10));
/* 24 */
        this.sprite = Sprite.particle_normal;
/*    */ 
/* 26 */
        this.xa = this.random.nextGaussian();
/* 27 */
        this.ya = this.random.nextGaussian();
/* 28 */
        this.zz = (this.random.nextFloat() + 2.0D);
/*    */
    }

    /*    */
/*    */
    public void update() {
/* 32 */
        this.time += 1;
/* 33 */
        if (this.time >= 7400) this.time = 0;
/* 34 */
        if (this.time > this.life) remove();
/* 35 */
        this.za -= 0.1D;
/*    */ 
/* 37 */
        if (this.zz < 0.0D) {
/* 38 */
            this.zz = 0.0D;
/* 39 */
            this.za *= -0.55D;
/* 40 */
            this.xa *= 0.4D;
/* 41 */
            this.ya *= 0.4D;
/*    */
        }
/*    */ 
/* 44 */
        move(this.xx + this.xa, this.yy + this.ya + (this.zz + this.za));
/*    */
    }

    /*    */
/*    */
    private void move(double x, double y)
/*    */ {
/* 49 */
        if (collision(x, y)) {
/* 50 */
            this.xa *= -0.5D;
/* 51 */
            this.ya *= -0.5D;
/* 52 */
            this.za *= -0.5D;
/*    */
        }
/* 54 */
        this.xx += this.xa;
/* 55 */
        this.yy += this.ya;
/* 56 */
        this.zz += this.za;
/*    */
    }

    /*    */
/*    */
    public boolean collision(double x, double y) {
/* 60 */
        boolean solid = false;
/* 61 */
        for (int c = 0; c < 4; c++) {
/* 62 */
            double xt = (x - c % 2 * 16) / 16.0D;
/* 63 */
            double yt = (y - c / 2 * 16) / 16.0D;
/* 64 */
            int ix = (int) Math.ceil(xt);
/* 65 */
            int iy = (int) Math.ceil(yt);
/* 66 */
            if (c % 2 == 0) ix = (int) Math.floor(xt);
/* 67 */
            if (c / 2 == 0) iy = (int) Math.floor(yt);
/* 68 */
            if (this.level.getTile(ix, iy).solid()) solid = true;
/*    */
        }
/* 70 */
        return solid;
/*    */
    }

    /*    */
/*    */
    public void render(Screen screen) {
/* 74 */
        screen.renderSprite((int) this.xx - 1, (int) this.yy - (int) this.zz - 1, this.sprite, true);
/*    */
    }
/*    */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.entity.particle.Particle
 * JD-Core Version:    0.6.2
 */