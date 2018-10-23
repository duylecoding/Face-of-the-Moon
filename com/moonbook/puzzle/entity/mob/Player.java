/*     */
package com.moonbook.puzzle.entity.mob;
/*     */ 
/*     */

import com.moonbook.puzzle.Game;
import com.moonbook.puzzle.entity.Entity;
import com.moonbook.puzzle.entity.projectile.BasicAttack;
import com.moonbook.puzzle.entity.projectile.Projectile;
import com.moonbook.puzzle.graphics.AnimatedSprite;
import com.moonbook.puzzle.graphics.Screen;
import com.moonbook.puzzle.graphics.Sprite;
import com.moonbook.puzzle.graphics.SpriteSheet;
import com.moonbook.puzzle.input.Keyboard;
import com.moonbook.puzzle.input.Mouse;
import com.moonbook.puzzle.sounds.Sounds;

import java.awt.*;

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
/*     */
/*     */

/*     */
/*     */ public class Player extends Mob
/*     */ {
    /*  28 */   public static int stuns = 1;
    /*     */   private Keyboard input;
    /*     */   private Sprite sprite;
    /*  21 */   private boolean walking = false;
    /*  22 */   private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 16, 16, 3);
    /*  23 */   private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 16, 16, 3);
    /*  24 */   private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 16, 16, 3);
    /*  25 */   private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 16, 16, 3);
    /*     */
/*  27 */   private AnimatedSprite animSprite = this.down;
    /*  29 */   private boolean damaged = false;
    /*  30 */   private int fireRate = 0;

    /*     */
/*     */
    public Player(Keyboard input)
/*     */ {
/*  34 */
        this.input = input;
/*  35 */
        this.sprite = Sprite.player_down;
/*     */
    }

    /*     */
/*     */
    public Player(int x, int y, Keyboard input) {
/*  39 */
        this.input = input;
/*  40 */
        this.x = x;
/*  41 */
        this.y = y;
/*  42 */
        this.health = 200;
/*  43 */
        this.sprite = Sprite.player_down;
/*  44 */
        this.fireRate = BasicAttack.FIRE_RATE;
/*  45 */
        this.rect = new Rectangle(x, y, 16, 16);
/*  46 */
        this.passable = false;
/*     */
    }

    /*     */
/*     */
    public void update() {
/*  50 */
        if (this.walking)
/*  51 */ this.animSprite.update();
/*     */
        else
/*  53 */       this.animSprite.setFrame(0);
/*  54 */
        if (this.fireRate > 0) this.fireRate -= 1;
/*  55 */
        double xa = 0.0D;
        double ya = 0.0D;
/*  56 */
        double speed = 1.0D;
/*  57 */
        this.rect.setLocation(this.x, this.y);
/*     */ 
/*  59 */
        if (this.input.up) {
/*  60 */
            ya -= speed;
/*  61 */
            this.animSprite = this.up;
/*  62 */
        } else if (this.input.down) {
/*  63 */
            ya += speed;
/*  64 */
            this.animSprite = this.down;
/*     */
        }
/*     */ 
/*  67 */
        if (this.input.left) {
/*  68 */
            xa -= speed;
/*  69 */
            this.animSprite = this.left;
/*  70 */
        } else if (this.input.right) {
/*  71 */
            xa += speed;
/*  72 */
            this.animSprite = this.right;
/*     */
        }
/*     */ 
/*  75 */
        if ((xa != 0.0D) || (ya != 0.0D)) {
/*  76 */
            move(xa, ya);
/*  77 */
            this.walking = true;
/*     */
        }
/*     */
        else {
/*  80 */
            this.walking = false;
/*     */
        }
/*     */ 
/*  83 */
        mobCol();
/*  84 */
        clear();
/*  85 */
        updateShooting();
/*     */
    }

    /*     */
/*     */
    public void mobCol() {
/*  89 */
        for (int i = 0; i < this.level.getEntities().size(); i++) {
/*  90 */
            Entity e = (Entity) this.level.getEntities().get(i);
/*  91 */
            if (e.getRect().intersects(getRect()))
/*  92 */ this.damaged = true;
/*     */
        }
/*     */
    }

    /*     */
/*     */
    public boolean isDamaged()
/*     */ {
/*  99 */
        return this.damaged;
/*     */
    }

    /*     */
/*     */
    public void resetDmg() {
/* 103 */
        this.damaged = false;
/*     */
    }

    /*     */
/*     */
    private void clear() {
/* 107 */
        for (int i = 0; i < this.level.getProjectiles().size(); i++) {
/* 108 */
            Projectile p = (Projectile) this.level.getProjectiles().get(i);
/* 109 */
            if (p.isRemoved()) this.level.getProjectiles().remove(i);
/*     */
        }
/*     */
    }

    /*     */
/*     */
    private void updateShooting()
/*     */ {
/* 114 */
        if ((Mouse.getButton() == 1) && (this.fireRate <= 0)) {
/* 115 */
            double dx = Mouse.getX() - Game.getWindowWidth() / 2;
/* 116 */
            double dy = Mouse.getY() - Game.getWindowHeight() / 2;
/* 117 */
            double dir = Math.atan2(dy, dx);
/* 118 */
            shoot(this.x, this.y, dir);
/* 119 */
            Sounds.BASICSHOT.play();
/* 120 */
            this.fireRate = BasicAttack.FIRE_RATE;
/*     */
        }
/* 122 */
        if (stuns != 0)
/*     */ {
/* 125 */
            if ((Mouse.getButton() == 3) && (this.fireRate <= 0)) {
/* 126 */
                double dx = Mouse.getX() - Game.getWindowWidth() / 2;
/* 127 */
                double dy = Mouse.getY() - Game.getWindowHeight() / 2;
/* 128 */
                double dir = Math.atan2(dy, dx);
/* 129 */
                shootSlow(this.x, this.y, dir);
/* 130 */
                Sounds.FROSTSHOT.play();
/* 131 */
                this.fireRate = BasicAttack.FIRE_RATE;
/* 132 */
                stuns -= 1;
/*     */
            }
/*     */
        }
/*     */
    }

    /*     */
/*     */
    public void render(Screen screen)
/*     */ {
/* 139 */
        this.sprite = this.animSprite.getSprite();
/* 140 */
        screen.renderMob(this.x, this.y, this.sprite);
/*     */
    }
/*     */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.entity.mob.Player
 * JD-Core Version:    0.6.2
 */