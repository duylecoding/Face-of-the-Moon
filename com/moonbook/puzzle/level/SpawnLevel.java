/*    */
package com.moonbook.puzzle.level;
/*    */ 
/*    */

import com.moonbook.puzzle.entity.mob.BlueTotem;
import com.moonbook.puzzle.entity.mob.Robot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*    */
/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ public class SpawnLevel extends Level
/*    */ {
    /*    */
    public SpawnLevel(String path)
/*    */ {
/* 15 */
        super(path);
/*    */
    }

    /*    */
/*    */
    protected void loadLevel(String path) {
/*    */
        try {
/* 20 */
            BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
/* 21 */
            int w = this.width = image.getWidth();
/* 22 */
            int h = this.height = image.getHeight();
/* 23 */
            this.tiles = new int[w * h];
/* 24 */
            image.getRGB(0, 0, w, h, this.tiles, 0, w);
/*    */
        } catch (IOException e) {
/* 26 */
            e.printStackTrace();
/* 27 */
            System.out.println("SPAWNLEVEL CLASS! COULD NOT LOAD LEVEL");
/*    */
        }
/* 29 */
        add(new BlueTotem(18, 20));
/* 30 */
        add(new Robot(20, 1));
/*    */
    }

    /*    */
/*    */
    protected void generateLevel()
/*    */ {
/*    */
    }
/*    */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.level.SpawnLevel
 * JD-Core Version:    0.6.2
 */