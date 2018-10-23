/*    */
package com.moonbook.puzzle.input;
/*    */ 
/*    */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*    */

/*    */
/*    */ public class Keyboard
/*    */ implements KeyListener
/*    */ {
    /*    */   public boolean up;
    /*    */   public boolean down;
    /*    */   public boolean left;
    /*    */   public boolean right;
    /*    */   public boolean restart;
    /*  8 */   private boolean[] keys = new boolean[150];

    /*    */
/*    */
    public void update()
/*    */ {
/* 12 */
        this.up = (keys[KeyEvent.VK_UP] || (keys[KeyEvent.VK_W]));
/* 13 */
        this.down = (keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S]);
        this.left = (keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A]);
/* 15 */
        this.right = (keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D]);
/* 16 */
        this.restart = keys[KeyEvent.VK_R];
/*    */
    }

    /*    */
/*    */
    public void keyPressed(KeyEvent e)
/*    */ {
/* 21 */
        this.keys[e.getKeyCode()] = true;
/*    */
    }

    /*    */
/*    */
    public void keyReleased(KeyEvent e)
/*    */ {
/* 26 */
        this.keys[e.getKeyCode()] = false;
/*    */
    }

    /*    */
/*    */
    public void keyTyped(KeyEvent e)
/*    */ {
/*    */
    }
/*    */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.input.Keyboard
 * JD-Core Version:    0.6.2
 */