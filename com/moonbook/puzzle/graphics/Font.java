/*    */
package com.moonbook.puzzle.graphics;

/*    */
/*    */ public class Font
/*    */ {
    /*  5 */   private static SpriteSheet font = new SpriteSheet("/fonts/arial.png", 16);
    /*  6 */   private static Sprite[] characters = Sprite.split(font);

    /*    */
/*    */
    public void render(Screen screen)
/*    */ {
/* 13 */
        screen.renderSprite(50, 50, characters[0], false);
/*    */
    }
/*    */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.graphics.Font
 * JD-Core Version:    0.6.2
 */