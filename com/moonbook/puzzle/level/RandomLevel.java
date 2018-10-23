/*    */
package com.moonbook.puzzle.level;
/*    */ 
/*    */

import java.util.Random;

/*    */
/*    */ public class RandomLevel extends Level
/*    */ {
    /*  7 */   private static final Random random = new Random();

    /*    */
/*    */
    public RandomLevel(int width, int height) {
/* 10 */
        super(width, height);
/*    */
    }

    /*    */
/*    */
    protected void generateLevel() {
/* 14 */
        for (int y = 0; y < this.height; y++)
/* 15 */
            for (int x = 0; x < this.width; x++)
/* 16 */
                this.tilesInt[(x + y * this.width)] = random.nextInt(4);
/*    */
    }
/*    */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.level.RandomLevel
 * JD-Core Version:    0.6.2
 */