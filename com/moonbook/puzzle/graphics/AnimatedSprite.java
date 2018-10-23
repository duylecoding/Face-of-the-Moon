/*    */
package com.moonbook.puzzle.graphics;
/*    */ 
/*    */

/*    */
/*    */ public class AnimatedSprite extends Sprite
/*    */ {
    /*  5 */   private int frame = 0;
    /*    */   private Sprite sprite;
    /*  7 */   private int rate = 5;
    /*  8 */   private int time = 0;
    /*  9 */   private int length = -1;

    /*    */
/*    */
    public AnimatedSprite(SpriteSheet sheet, int width, int height, int length) {
/* 12 */
        super(sheet, width, height);
/* 13 */
        this.length = length;
/* 14 */
        this.sprite = sheet.getSprites()[0];
/* 15 */
        if (length > sheet.getSprites().length) System.err.println("Error! Length of animation is too long");
/*    */
    }

    /*    */
/*    */
    public void update()
/*    */ {
/* 19 */
        this.time += 1;
/* 20 */
        if (this.time % this.rate == 0) {
/* 21 */
            if (this.frame >= this.length - 1) this.frame = 0;
            else
/* 22 */         this.frame += 1;
/* 23 */
            this.sprite = this.sheet.getSprites()[this.frame];
/*    */
        }
/*    */
    }

    /*    */
/*    */
    public Sprite getSprite() {
/* 28 */
        return this.sprite;
/*    */
    }

    /*    */
/*    */
    public void setFrameRate(int frames)
/*    */ {
/*    */
    }

    /*    */
/*    */
    public void setFrame(int index) {
/* 36 */
        if (index > this.sheet.getSprites().length - 1) {
/* 37 */
            System.err.println("Index Out of bounds" + this);
/* 38 */
            return;
/*    */
        }
/* 40 */
        this.sprite = this.sheet.getSprites()[index];
/*    */
    }
/*    */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.graphics.AnimatedSprite
 * JD-Core Version:    0.6.2
 */