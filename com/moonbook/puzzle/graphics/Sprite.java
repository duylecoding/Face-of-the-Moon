/*     */
package com.moonbook.puzzle.graphics;

/*     */
/*     */ public class Sprite
/*     */ {
    /*  12 */   public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
    /*  13 */   public static Sprite grass_flower = new Sprite(16, 0, 1, SpriteSheet.tiles);
    /*  14 */   public static Sprite grass_rock = new Sprite(16, 0, 2, SpriteSheet.tiles);
    /*  15 */   public static Sprite voidSprite = new Sprite(16, 2, 2, SpriteSheet.spawn);
    /*  16 */   public static Sprite stone_floor = new Sprite(16, 1, 0, SpriteSheet.spawn);
    /*  17 */   public static Sprite stone_wall = new Sprite(16, 1, 1, SpriteSheet.spawn);
    /*  18 */   public static Sprite bed = new Sprite(16, 2, 0, SpriteSheet.spawn);
    /*  19 */   public static Sprite path = new Sprite(16, 2, 1, SpriteSheet.spawn);
    /*  20 */   public static Sprite ironDoor_open = new Sprite(16, 2, 2, SpriteSheet.spawn);
    /*  21 */   public static Sprite ironDoor_closed = new Sprite(16, 1, 2, SpriteSheet.spawn);
    /*  22 */   public static Sprite bookshelf = new Sprite(16, 0, 0, SpriteSheet.spawn);
    /*  23 */   public static Sprite dark = new Sprite(16, 2, 2, SpriteSheet.spawn);
    /*     */
/*  27 */   public static Sprite basic_Att = new Sprite(16, 5, 1, SpriteSheet.tiles);
    /*  28 */   public static Sprite slow_Att = new Sprite(16, 5, 2, SpriteSheet.tiles);
    /*     */
/*  31 */   public static Sprite particle_normal = new Sprite(3, -10240);
    /*  32 */   public static Sprite particle_blue = new Sprite(3, -12386321);
    /*     */
/*  36 */   public static Sprite player_down = new Sprite(16, 1, 0, SpriteSheet.tiles);
    /*  37 */   public static Sprite player_up = new Sprite(16, 2, 0, SpriteSheet.tiles);
    /*  38 */   public static Sprite player_left = new Sprite(16, 4, 0, SpriteSheet.tiles);
    /*  39 */   public static Sprite player_right = new Sprite(16, 3, 0, SpriteSheet.tiles);
    /*     */
/*  41 */   public static Sprite pimpkid3 = new Sprite(16, 0, 3, SpriteSheet.tiles);
    /*  42 */   public static Sprite blueTotem = new Sprite(32, 7, 0, SpriteSheet.tiles);
    /*     */   public final int SIZE;
    /*     */   public int[] pixels;
    /*     */   protected SpriteSheet sheet;
    /*     */   private int height;
    /*     */   private int width;
    /*     */   private int x;
    /*     */   private int y;

    /*     */
/*     */
    protected Sprite(SpriteSheet sheet, int width, int height)
/*     */ {
/*  46 */
        if (width == height) this.SIZE = width;
        else {
/*  47 */
            this.SIZE = -1;
/*     */
        }
/*  49 */
        this.width = width;
/*  50 */
        this.height = height;
/*  51 */
        this.sheet = sheet;
/*     */
    }

    /*     */
/*     */
    public Sprite(int size, int x, int y, SpriteSheet sheet) {
/*  55 */
        this.SIZE = size;
/*  56 */
        this.width = size;
/*  57 */
        this.height = size;
/*  58 */
        this.pixels = new int[this.SIZE * this.SIZE];
/*  59 */
        this.x = (x * size);
/*  60 */
        this.y = (y * size);
/*  61 */
        this.sheet = sheet;
/*  62 */
        load();
/*     */
    }

    /*     */
/*     */
    public Sprite(int width, int height, int color) {
/*  66 */
        this.SIZE = -1;
/*  67 */
        this.width = width;
/*  68 */
        this.height = height;
/*  69 */
        this.pixels = new int[width * height];
/*  70 */
        setColor(color);
/*     */
    }

    /*     */
/*     */
    public Sprite(int size, int color) {
/*  74 */
        this.SIZE = size;
/*  75 */
        this.width = size;
/*  76 */
        this.height = size;
/*  77 */
        this.pixels = new int[this.SIZE * this.SIZE];
/*  78 */
        setColor(color);
/*     */
    }

    /*     */
/*     */
    public Sprite(int[] pixels, int width, int height) {
/*  82 */
        if (width == height) this.SIZE = width;
        else
/*  83 */       this.SIZE = -1;
/*  84 */
        this.width = width;
/*  85 */
        this.height = height;
/*  86 */
        this.pixels = pixels;
/*     */
    }

    /*     */
/*     */
    public static Sprite rotate(Sprite sprite, double angle) {
/*  90 */
        return new Sprite(rotate(sprite.pixels, sprite.width, sprite.height, angle), sprite.width, sprite.height);
/*     */
    }

    /*     */
/*     */
    private static int[] rotate(int[] pixels, int width, int height, double angle) {
/*  94 */
        int[] result = new int[width * height];
/*     */ 
/*  96 */
        double nx_x = rotX(-angle, 1.0D, 0.0D);
/*  97 */
        double nx_y = rotY(-angle, 1.0D, 0.0D);
/*  98 */
        double ny_x = rotX(-angle, 0.0D, 1.0D);
/*  99 */
        double ny_y = rotY(-angle, 0.0D, 1.0D);
/*     */ 
/* 101 */
        double x0 = rotX(-angle, -width / 2.0D, -height / 2.0D) + width / 2.0D;
/* 102 */
        double y0 = rotY(-angle, -width / 2.0D, -height / 2.0D) + height / 2.0D;
/*     */ 
/* 104 */
        for (int y = 0; y < height; y++) {
/* 105 */
            double x1 = x0;
/* 106 */
            double y1 = y0;
/* 107 */
            for (int x = 0; x < width; x++) {
/* 108 */
                int xx = (int) x1;
/* 109 */
                int yy = (int) y1;
/* 110 */
                int col = 0;
/* 111 */
                if ((xx < 0) || (xx >= width) || (yy < 0) || (yy >= height)) col = -32787;
                else
/* 112 */           col = pixels[(xx + yy * width)];
/* 113 */
                result[(x + y * width)] = col;
/* 114 */
                x1 += nx_x;
/* 115 */
                y1 += nx_y;
/*     */
            }
/* 117 */
            x0 += ny_x;
/* 118 */
            y0 += ny_y;
/*     */
        }
/* 120 */
        return result;
/*     */
    }

    /*     */
/*     */
    private static double rotX(double angle, double x, double y) {
/* 124 */
        double cos = Math.cos(angle - 1.570796326794897D);
/* 125 */
        double sin = Math.sin(angle - 1.570796326794897D);
/* 126 */
        return x * cos + y * -sin;
/*     */
    }

    /*     */
/*     */
    private static double rotY(double angle, double x, double y) {
/* 130 */
        double cos = Math.cos(angle - 1.570796326794897D);
/* 131 */
        double sin = Math.sin(angle - 1.570796326794897D);
/* 132 */
        return x * sin + y * cos;
/*     */
    }

    /*     */
/*     */
    public static Sprite[] split(SpriteSheet sheet)
/*     */ {
/* 137 */
        int amount = sheet.getWidth() * sheet.getHeight() / (sheet.SPRITE_WIDTH * sheet.SPRITE_HEIGHT);
/* 138 */
        Sprite[] sprites = new Sprite[amount];
/* 139 */
        int current = 0;
/* 140 */
        int[] pixels = new int[sheet.SPRITE_WIDTH * sheet.SPRITE_HEIGHT];
/*     */ 
/* 142 */
        for (int yp = 0; yp < sheet.getHeight() / sheet.SPRITE_HEIGHT; yp++) {
/* 143 */
            for (int xp = 0; xp < sheet.getWidth() / sheet.SPRITE_WIDTH; xp++)
/*     */ {
/* 145 */
                for (int y = 0; y < sheet.SPRITE_HEIGHT; y++) {
/* 146 */
                    for (int x = 0; x < sheet.SPRITE_WIDTH; x++) {
/* 147 */
                        int xo = x + xp * sheet.SPRITE_WIDTH;
/* 148 */
                        int yo = y + yp * sheet.SPRITE_HEIGHT;
/* 149 */
                        pixels[(x + y * sheet.SPRITE_WIDTH)] = sheet.getPixels()[(xo + yo * sheet.getWidth())];
/*     */
                    }
/*     */
                }
/*     */ 
/* 153 */
                sprites[(current++)] = new Sprite(pixels, sheet.SPRITE_WIDTH, sheet.SPRITE_HEIGHT);
/*     */
            }
/*     */
        }
/*     */ 
/* 157 */
        return sprites;
/*     */
    }

    /*     */
/*     */
    private void setColor(int color) {
/* 161 */
        for (int i = 0; i < this.width * this.height; i++)
/* 162 */
            this.pixels[i] = color;
/*     */
    }

    /*     */
/*     */
    public int getWidth()
/*     */ {
/* 167 */
        return this.width;
/*     */
    }

    /*     */
    public int getHeight() {
/* 170 */
        return this.height;
/*     */
    }

    /*     */
/*     */
    private void load() {
/* 174 */
        for (int y = 0; y < this.height; y++)
/* 175 */
            for (int x = 0; x < this.width; x++)
/* 176 */
                this.pixels[(x + y * this.width)] = this.sheet.pixels[(x + this.x + (y + this.y) * this.sheet.SPRITE_WIDTH)];
/*     */
    }
/*     */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.graphics.Sprite
 * JD-Core Version:    0.6.2
 */