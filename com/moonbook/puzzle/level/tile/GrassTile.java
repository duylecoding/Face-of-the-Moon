/*    */
package com.moonbook.puzzle.level.tile;
/*    */ 
/*    */

import com.moonbook.puzzle.graphics.Screen;
import com.moonbook.puzzle.graphics.Sprite;

/*    */

/*    */
/*    */ public class GrassTile extends Tile
/*    */ {
    /*    */
    public GrassTile(Sprite sprite)
/*    */ {
/*  9 */
        super(sprite);
/*    */
    }

    /*    */
/*    */
    public void render(int x, int y, Screen screen) {
/* 13 */
        screen.renderTile(x << 4, y << 4, this);
/*    */
    }
/*    */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.level.tile.GrassTile
 * JD-Core Version:    0.6.2
 */