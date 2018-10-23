/*    */
package com.moonbook.puzzle.util;

/*    */
/*    */ public class Vector2i
/*    */ {
    /*    */   private int x;
    /*    */   private int y;

    /*    */
/*    */
    public Vector2i()
/*    */ {
/*  8 */
        set(0, 0);
/*    */
    }

    /*    */
/*    */
    public Vector2i(Vector2i vector) {
/* 12 */
        set(vector.x, vector.y);
/*    */
    }

    /*    */
/*    */
    public Vector2i(int x, int y) {
/* 16 */
        set(x, y);
/*    */
    }

    /*    */
/*    */
    public static double getDistance(Vector2i v0, Vector2i v1) {
/* 55 */
        double x = v0.getX() - v1.getX();
/* 56 */
        double y = v0.getY() - v1.getY();
/* 57 */
        return Math.sqrt(x * x + y * y);
/*    */
    }

    /*    */
/*    */
    public void set(int x, int y) {
/* 20 */
        this.x = x;
/* 21 */
        this.y = y;
/*    */
    }

    /*    */
/*    */
    public int getX() {
/* 25 */
        return this.x;
/*    */
    }

    /*    */
/*    */
    public Vector2i setX(int x) {
/* 45 */
        this.x = x;
/* 46 */
        return this;
/*    */
    }

    /*    */
/*    */
    public int getY() {
/* 29 */
        return this.y;
/*    */
    }

    /*    */
/*    */
    public Vector2i setY(int y) {
/* 50 */
        this.y = y;
/* 51 */
        return this;
/*    */
    }

    /*    */
/*    */
    public Vector2i add(Vector2i vector) {
/* 33 */
        this.x += vector.x;
/* 34 */
        this.y += vector.y;
/* 35 */
        return this;
/*    */
    }

    /*    */
/*    */
    public Vector2i subtract(Vector2i vector) {
/* 39 */
        this.x -= vector.x;
/* 40 */
        this.y -= vector.y;
/* 41 */
        return this;
/*    */
    }

    /*    */
/*    */
    public boolean equals(Object object)
/*    */ {
/* 62 */
        if (!(object instanceof Vector2i)) return false;
/* 63 */
        Vector2i vec = (Vector2i) object;
/* 64 */
        if ((vec.getX() == getX()) && (vec.getY() == getY())) return true;
/* 65 */
        return false;
/*    */
    }
/*    */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.util.Vector2i
 * JD-Core Version:    0.6.2
 */