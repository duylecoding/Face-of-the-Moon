/*    */
package com.moonbook.puzzle.level.tile;
/*    */ 
/*    */

import com.moonbook.puzzle.graphics.Screen;
import com.moonbook.puzzle.graphics.Sprite;
import com.moonbook.puzzle.level.tile.spawn.*;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ public class Tile
/*    */ {
    /*    */   public static final int col_stone_floor = -65316;
    /*    */   public static final int col_stone_wall = -5888;
    /*    */   public static final int col_bed = -8192020;
    /*    */   public static final int col_path = -6587547;
    /*    */   public static final int col_ironDoor_closed = -41357;
    /*    */   public static final int col_bookshelf = -7581697;
    /*    */   public static final int col_dark = -16777216;
    /* 16 */   public static Tile grass = new GrassTile(Sprite.grass);
    /* 17 */   public static Tile grass_flower = new GrassFlowerTile(Sprite.grass_flower);
    /* 18 */   public static Tile grass_rock = new GrassRockTile(Sprite.grass_rock);
    /* 19 */   public static Tile stone_floor = new Stone_floor(Sprite.stone_floor);
    /* 20 */   public static Tile stone_wall = new Stone_wall(Sprite.stone_wall);
    /* 21 */   public static Tile bed = new Bed(Sprite.bed);
    /* 22 */   public static Tile path = new Path(Sprite.path);
    /* 23 */   public static Tile ironDoor_closed = new IronDoor(Sprite.ironDoor_closed);
    /* 24 */   public static Tile bookshelf = new BookShelf(Sprite.bookshelf);
    /* 25 */   public static Tile dark = new Dark(Sprite.dark);
    /* 36 */   public static Tile voidTile = new VoidTitle(Sprite.voidSprite);
    /*    */   public Sprite sprite;

    /*    */
/*    */
    public Tile(Sprite sprite) {
/* 39 */
        this.sprite = sprite;
/*    */
    }

    /*    */
/*    */
    public void render(int x, int y, Screen screen)
/*    */ {
/*    */
    }

    /*    */
/*    */
    public boolean solid()
/*    */ {
/* 48 */
        return false;
/*    */
    }
/*    */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.level.tile.Tile
 * JD-Core Version:    0.6.2
 */