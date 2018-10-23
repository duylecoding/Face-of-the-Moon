/*    */
package com.moonbook.puzzle.level.tile.spawn;
/*    */ 
/*    */

import com.moonbook.puzzle.graphics.Screen;
import com.moonbook.puzzle.graphics.Sprite;
import com.moonbook.puzzle.level.tile.Tile;

/*    */
/*    */

/*    */
/*    */ public class Stone_wall extends Tile
/*    */ {
    /*    */
    public Stone_wall(Sprite sprite)
/*    */ {
/* 10 */
        super(sprite);
/*    */
    }

    /*    */
/*    */
    public void render(int x, int y, Screen screen) {
/* 14 */
        screen.renderTile(x << 4, y << 4, this);
/*    */
    }

    /*    */
/*    */
    public boolean solid() {
/* 18 */
        return true;
/*    */
    }
/*    */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.level.tile.spawn.Stone_wall
 * JD-Core Version:    0.6.2
 */