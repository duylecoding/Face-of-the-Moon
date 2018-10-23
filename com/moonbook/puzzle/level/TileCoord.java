/*    */
package com.moonbook.puzzle.level;

/*    */
/*    */ public class TileCoord
/*    */ {
    /*  6 */   private final int TILE_SIZE = 16;
    /*    */   private int x;
    /*    */   private int y;

    /*    */
/*    */
    public TileCoord(int x, int y) {
/*  9 */
        this.x = (x * 16);
/* 10 */
        this.y = (y * 16);
/*    */
    }

    /*    */
/*    */
    public int x() {
/* 14 */
        return this.x;
/*    */
    }

    /*    */
/*    */
    public int y() {
/* 18 */
        return this.y;
/*    */
    }

    /*    */
/*    */
    public int[] xy() {
/* 22 */
        int[] r = new int[2];
/* 23 */
        r[0] = this.x;
/* 24 */
        r[1] = this.y;
/* 25 */
        return r;
/*    */
    }
/*    */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.level.TileCoord
 * JD-Core Version:    0.6.2
 */