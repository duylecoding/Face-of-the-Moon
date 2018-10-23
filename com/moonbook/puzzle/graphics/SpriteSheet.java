/*     */
package com.moonbook.puzzle.graphics;
/*     */ 
/*     */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*     */
/*     */
/*     */

/*     */
/*     */ public class SpriteSheet
/*     */ {
    /*  17 */   public static SpriteSheet tiles = new SpriteSheet("/textures/sheets/spritesheet.png", 256);
    /*  18 */   public static SpriteSheet spawn = new SpriteSheet("/textures/sheets/spawn.png", 48);
    /*     */
/*  20 */   public static SpriteSheet player_down = new SpriteSheet(tiles, 1, 0, 1, 3, 16);
    /*  21 */   public static SpriteSheet player_up = new SpriteSheet(tiles, 2, 0, 1, 3, 16);
    /*  22 */   public static SpriteSheet player_right = new SpriteSheet(tiles, 3, 0, 1, 3, 16);
    /*  23 */   public static SpriteSheet player_left = new SpriteSheet(tiles, 4, 0, 1, 3, 16);
    /*     */
/*  25 */   public static SpriteSheet pimpkid3_down = new SpriteSheet(tiles, 0, 3, 1, 3, 16);
    /*  26 */   public static SpriteSheet pimpkid3_up = new SpriteSheet(tiles, 1, 3, 1, 3, 16);
    /*  27 */   public static SpriteSheet pimpkid3_right = new SpriteSheet(tiles, 2, 3, 1, 3, 16);
    /*  28 */   public static SpriteSheet pimpkid3_left = new SpriteSheet(tiles, 3, 3, 1, 3, 16);
    /*     */
/*  30 */   public static SpriteSheet blueTotem = new SpriteSheet(tiles, 7, 0, 1, 3, 32);
    /*  31 */   public static SpriteSheet robot = new SpriteSheet(tiles, 0, 3, 1, 3, 32);
    /*  32 */   public static SpriteSheet robot_back = new SpriteSheet(tiles, 1, 3, 1, 3, 32);
    /*     */   public final int SIZE;
    /*     */   public final int SPRITE_WIDTH;
    /*     */   public final int SPRITE_HEIGHT;
    /*     */   public int[] pixels;
    /*     */   private String path;
    /*     */   private int width;
    /*     */   private int height;
    /*     */   private Sprite[] sprites;

    /*     */
/*     */
    public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize)
/*     */ {
/*  36 */
        int xx = x * spriteSize;
/*  37 */
        int yy = y * spriteSize;
/*  38 */
        int w = width * spriteSize;
/*  39 */
        int h = height * spriteSize;
/*  40 */
        if (width == height) this.SIZE = width;
        else
/*  41 */       this.SIZE = -1;
/*  42 */
        this.SPRITE_WIDTH = w;
/*  43 */
        this.SPRITE_HEIGHT = h;
/*  44 */
        this.pixels = new int[w * h];
/*  45 */
        for (int y0 = 0; y0 < h; y0++) {
/*  46 */
            int yp = yy + y0;
/*  47 */
            for (int x0 = 0; x0 < w; x0++) {
/*  48 */
                int xp = xx + x0;
/*  49 */
                this.pixels[(x0 + y0 * w)] = sheet.pixels[(xp + yp * sheet.SPRITE_WIDTH)];
/*     */
            }
/*     */
        }
/*  52 */
        int frame = 0;
/*  53 */
        this.sprites = new Sprite[width * height];
/*  54 */
        for (int ya = 0; ya < height; ya++)
/*  55 */
            for (int xa = 0; xa < width; xa++) {
/*  56 */
                int[] spritePixels = new int[spriteSize * spriteSize];
/*  57 */
                for (int y0 = 0; y0 < spriteSize; y0++) {
/*  58 */
                    for (int x0 = 0; x0 < spriteSize; x0++) {
/*  59 */
                        spritePixels[(x0 + y0 * spriteSize)] = this.pixels[(x0 + xa * spriteSize + (y0 + ya * spriteSize) * this.SPRITE_WIDTH)];
/*     */
                    }
/*     */
                }
/*  62 */
                Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
/*  63 */
                this.sprites[(frame++)] = sprite;
/*     */
            }
/*     */
    }

    /*     */
/*     */
    public SpriteSheet(String path, int size)
/*     */ {
/*  70 */
        this.path = path;
/*  71 */
        this.SIZE = size;
/*  72 */
        this.SPRITE_WIDTH = size;
/*  73 */
        this.SPRITE_HEIGHT = size;
/*  74 */
        this.pixels = new int[this.SPRITE_WIDTH * this.SPRITE_HEIGHT];
/*  75 */
        load();
/*     */
    }

    /*     */
/*     */
    public SpriteSheet(String path, int width, int height)
/*     */ {
/*  80 */
        this.path = path;
/*  81 */
        this.SIZE = -1;
/*  82 */
        this.SPRITE_WIDTH = width;
/*  83 */
        this.SPRITE_HEIGHT = height;
/*  84 */
        this.pixels = new int[this.SPRITE_WIDTH * this.SPRITE_HEIGHT];
/*  85 */
        load();
/*     */
    }

    /*     */
/*     */
    public Sprite[] getSprites() {
/*  89 */
        return this.sprites;
/*     */
    }

    /*     */
/*     */
    public int getWidth() {
/*  93 */
        return this.width;
/*     */
    }

    /*     */
/*     */
    public int getHeight() {
/*  97 */
        return this.height;
/*     */
    }

    /*     */
/*     */
    public int[] getPixels() {
/* 101 */
        return this.pixels;
/*     */
    }

    /*     */
/*     */
    private void load() {
/*     */
        try {
/* 106 */
            System.out.print("loading:" + this.path + " ...");
/* 107 */
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(this.path));
/* 108 */
            System.out.println(" success!");
/* 109 */
            this.width = image.getWidth();
/* 110 */
            this.height = image.getHeight();
/* 111 */
            this.pixels = new int[this.width * this.height];
/* 112 */
            image.getRGB(0, 0, this.width, this.height, this.pixels, 0, this.width);
/*     */
        } catch (IOException e) {
/* 114 */
            e.printStackTrace();
/*     */
        } catch (Exception e) {
/* 116 */
            System.err.println(" failed!");
/*     */
        }
/*     */
    }
/*     */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.graphics.SpriteSheet
 * JD-Core Version:    0.6.2
 */