/*    */
package com.moonbook.puzzle.util;
/*    */ 
/*    */

import com.moonbook.puzzle.graphics.Screen;

import java.awt.*;

/*    */

/*    */
/*    */ public class Debug
/*    */ {
    /*    */
    public static void drawRect(Screen screen, int x, int y, int width, int height, boolean fixed)
/*    */ {
/* 13 */
        drawRect(screen, x, y, width, height, 16711680, fixed);
/*    */
    }

    /*    */
/*    */
    public static void drawRect(Screen screen, int x, int y, int width, int height, int col, boolean fixed) {
/* 17 */
        screen.drawRect(x, y, width, height, col, fixed);
/*    */
    }

    /*    */
/*    */
    public static void drawRect(Screen screen, Rectangle rec, boolean fixed) {
/* 21 */
        drawRect(screen, rec.x, rec.y, rec.width, rec.height, 16711680, fixed);
/*    */
    }
/*    */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.util.Debug
 * JD-Core Version:    0.6.2
 */