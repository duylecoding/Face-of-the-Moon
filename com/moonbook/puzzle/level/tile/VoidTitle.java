/*    */
package com.moonbook.puzzle.level.tile;
/*    */ 
/*    */

import com.moonbook.puzzle.graphics.Screen;
import com.moonbook.puzzle.graphics.Sprite;

/*    */

/*    */
/*    */ public class VoidTitle extends Tile
/*    */ {
    /*    */
    public VoidTitle(Sprite sprite)
/*    */ {
/*  9 */
        super(sprite);
/*    */
    }

    /*    */
/*    */
    public void render(int x, int y, Screen screen)
/*    */ {
/* 14 */
        screen.renderTile(x << 4, y << 4, this);
/*    */
    }
/*    */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.level.tile.VoidTitle
 * JD-Core Version:    0.6.2
 */