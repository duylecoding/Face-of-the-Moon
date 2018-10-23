/*     */
package com.moonbook.puzzle.graphics;
/*     */ 
/*     */

import com.moonbook.puzzle.entity.mob.Chaser;
import com.moonbook.puzzle.entity.mob.Mob;
import com.moonbook.puzzle.entity.mob.Mob32;
import com.moonbook.puzzle.entity.projectile.Projectile;
import com.moonbook.puzzle.level.tile.Tile;

import java.util.Random;

/*     */
/*     */
/*     */
/*     */
/*     */

/*     */
/*     */ public class Screen
/*     */ {
    /*     */   public int width;
    /*     */   public int height;
    /*     */   public int[] pixels;
    /*  16 */   public int MAP_SIZE = 64;
    /*  17 */   public int MAP_SIZE_MASK = this.MAP_SIZE - 1;
    /*  18 */   public int[] tiles = new int[this.MAP_SIZE * this.MAP_SIZE];
    /*     */   public int xOffset;
    /*     */   public int yOffset;
    /*  22 */   private Random random = new Random();

    /*     */
/*     */
    public Screen(int width, int height) {
/*  25 */
        this.width = width;
/*  26 */
        this.height = height;
/*  27 */
        this.pixels = new int[width * height];
/*     */ 
/*  29 */
        for (int i = 0; i < this.MAP_SIZE * this.MAP_SIZE; i++) {
/*  30 */
            this.tiles[i] = this.random.nextInt(16777215);
/*  31 */
            this.tiles[0] = 0;
/*     */
        }
/*     */
    }

    /*     */
/*     */
    public void clear() {
/*  36 */
        for (int i = 0; i < this.pixels.length; i++)
/*  37 */
            this.pixels[i] = 0;
/*     */
    }

    /*     */
/*     */
    public void renderSheet(int xp, int yp, SpriteSheet sheet, boolean fixed)
/*     */ {
/*  43 */
        if (fixed) {
/*  44 */
            xp -= this.xOffset;
/*  45 */
            yp -= this.yOffset;
/*     */
        }
/*  47 */
        for (int y = 0; y < sheet.SPRITE_HEIGHT; y++) {
/*  48 */
            int ya = y + yp;
/*  49 */
            for (int x = 0; x < sheet.SPRITE_WIDTH; x++) {
/*  50 */
                int xa = x + xp;
/*  51 */
                if ((xa < 0) || (xa >= this.width) || (ya < 0) || (ya >= this.height)) break;
/*  52 */
                this.pixels[(xa + ya * this.width)] = sheet.pixels[(x + y * sheet.SPRITE_WIDTH)];
/*     */
            }
/*     */
        }
/*     */
    }

    /*     */
/*     */
    public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
/*  58 */
        if (fixed) {
/*  59 */
            xp -= this.xOffset;
/*  60 */
            yp -= this.yOffset;
/*     */
        }
/*  62 */
        for (int y = 0; y < sprite.getHeight(); y++) {
/*  63 */
            int ya = y + yp;
/*  64 */
            for (int x = 0; x < sprite.getWidth(); x++) {
/*  65 */
                int xa = x + xp;
/*  66 */
                if ((xa < 0) || (xa >= this.width) || (ya < 0) || (ya >= this.height)) break;
/*  67 */
                this.pixels[(xa + ya * this.width)] = sprite.pixels[(x + y * sprite.getWidth())];
/*     */
            }
/*     */
        }
/*     */
    }

    /*     */
/*     */
    public void renderTile(int xp, int yp, Tile tile) {
/*  73 */
        xp -= this.xOffset;
/*  74 */
        yp -= this.yOffset;
/*  75 */
        for (int y = 0; y < tile.sprite.SIZE; y++) {
/*  76 */
            int ya = y + yp;
/*  77 */
            for (int x = 0; x < tile.sprite.SIZE; x++) {
/*  78 */
                int xa = x + xp;
/*  79 */
                if ((xa < -tile.sprite.SIZE) || (xa >= this.width) || (ya < 0) || (ya >= this.height)) break;
/*  80 */
                if (xa < 0) xa = 0;
/*  81 */
                this.pixels[(xa + ya * this.width)] = tile.sprite.pixels[(x + y * tile.sprite.SIZE)];
/*     */
            }
/*     */
        }
/*     */
    }

    /*     */
/*     */
    public void renderProjectile(int xp, int yp, Projectile p) {
/*  87 */
        xp -= this.xOffset;
/*  88 */
        yp -= this.yOffset;
/*  89 */
        for (int y = 0; y < p.getSpriteSize(); y++) {
/*  90 */
            int ya = y + yp;
/*  91 */
            for (int x = 0; x < p.getSpriteSize(); x++) {
/*  92 */
                int xa = x + xp;
/*  93 */
                if ((xa < -p.getSpriteSize()) || (xa >= this.width) || (ya < 0) || (ya >= this.height)) break;
/*  94 */
                if (xa < 0) xa = 0;
/*  95 */
                int col = p.getSprite().pixels[(x + y * p.getSprite().SIZE)];
/*  96 */
                if (col != -32787) this.pixels[(xa + ya * this.width)] = col;
/*     */
            }
/*     */
        }
/*     */
    }

    /*     */
/*     */
    public void renderMob(int xp, int yp, Sprite sprite)
/*     */ {
/* 103 */
        xp -= this.xOffset;
/* 104 */
        yp -= this.yOffset;
/* 105 */
        for (int y = 0; y < 16; y++) {
/* 106 */
            int ya = y + yp;
/* 107 */
            for (int x = 0; x < 16; x++) {
/* 108 */
                int xa = x + xp;
/* 109 */
                if ((xa < -16) || (xa >= this.width) || (ya < 0) || (ya >= this.height)) break;
/* 110 */
                if (xa < 0) xa = 0;
/* 111 */
                int col = sprite.pixels[(x + y * 16)];
/* 112 */
                if (col != -32787) this.pixels[(xa + ya * this.width)] = col;
/*     */
            }
/*     */
        }
/*     */
    }

    /*     */
/*     */
    public void renderMob(int xp, int yp, Mob mob, int size)
/*     */ {
/* 118 */
        xp -= this.xOffset;
/* 119 */
        yp -= this.yOffset;
/* 120 */
        for (int y = 0; y < size; y++) {
/* 121 */
            int ya = y + yp;
/* 122 */
            for (int x = 0; x < size; x++) {
/* 123 */
                int xa = x + xp;
/* 124 */
                if ((xa < 0) || (xa >= this.width) || (ya < 0) || (ya >= this.height)) break;
/* 125 */
                if (xa < 0) xa = 0;
/* 126 */
                int col = mob.getSprite().pixels[(x + y * size)];
/* 127 */
                if (((mob instanceof Chaser)) && (col == -16777216)) col = -16767233;
/* 128 */
                if (col != -32787) this.pixels[(xa + ya * this.width)] = col;
/*     */
            }
/*     */
        }
/*     */
    }

    /*     */
/*     */
    public void renderMob(int xp, int yp, Mob32 mob, int size)
/*     */ {
/* 135 */
        xp -= this.xOffset;
/* 136 */
        yp -= this.yOffset;
/* 137 */
        for (int y = 0; y < size; y++) {
/* 138 */
            int ya = y + yp;
/* 139 */
            for (int x = 0; x < size; x++) {
/* 140 */
                int xa = x + xp;
/* 141 */
                if ((xa < 0) || (xa >= this.width) || (ya < 0) || (ya >= this.height)) break;
/* 142 */
                if (xa < 0) xa = 0;
/* 143 */
                int col = mob.getSprite().pixels[(x + y * size)];
/*     */ 
/* 145 */
                if (col != -32787) this.pixels[(xa + ya * this.width)] = col;
/*     */
            }
/*     */
        }
/*     */
    }

    /*     */
/*     */
    public void drawRect(int xp, int yp, int width, int height, int color, boolean fixed)
/*     */ {
/* 152 */
        if (fixed) {
/* 153 */
            xp -= this.xOffset;
/* 154 */
            yp -= this.yOffset;
/*     */
        }
/*     */ 
/* 157 */
        for (int x = xp; x < xp + width; x++) {
/* 158 */
            if ((x >= 0) && (x < this.width) && (yp < this.height)) {
/* 159 */
                if (yp > 0) this.pixels[(x + yp * this.width)] = color;
/* 160 */
                if ((yp + height < this.height) &&
/* 161 */           (yp + height > 0)) this.pixels[(x + (yp + height) * this.width)] = color;
/*     */
            }
/*     */
        }
/* 164 */
        for (int y = yp; y <= yp + height; y++)
/* 165 */
            if ((xp < this.width) && (y >= 0) && (y < this.height)) {
/* 166 */
                if (xp > 0) this.pixels[(xp + y * this.width)] = color;
/* 167 */
                if ((xp + width < this.width) &&
/* 168 */           (xp + width > 0)) this.pixels[(xp + width + y * this.width)] = color;
/*     */
            }
/*     */
    }

    /*     */
/*     */
    public void setOffset(int xOffset, int yOffset)
/*     */ {
/* 175 */
        this.xOffset = xOffset;
/* 176 */
        this.yOffset = yOffset;
/*     */
    }
/*     */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.graphics.Screen
 * JD-Core Version:    0.6.2
 */