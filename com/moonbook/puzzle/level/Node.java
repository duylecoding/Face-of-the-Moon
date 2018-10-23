/*    */
package com.moonbook.puzzle.level;
/*    */ 
/*    */

import com.moonbook.puzzle.util.Vector2i;

/*    */
/*    */ public class Node
/*    */ {
    /*    */   public Vector2i tile;
    /*    */   public Node parent;
    /*    */   public double fCost;
    /*    */   public double gCost;
    /*    */   public double hCost;

    /*    */
/*    */
    public Node(Vector2i tile, Node parent, double gCost, double hCost)
/*    */ {
/* 12 */
        this.tile = tile;
/* 13 */
        this.parent = parent;
/* 14 */
        this.gCost = gCost;
/* 15 */
        this.hCost = gCost;
/* 16 */
        this.fCost = (this.gCost + this.hCost);
/*    */
    }
/*    */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.level.Node
 * JD-Core Version:    0.6.2
 */