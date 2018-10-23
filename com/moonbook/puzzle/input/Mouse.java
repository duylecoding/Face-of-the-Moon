/*    */
package com.moonbook.puzzle.input;
/*    */ 
/*    */

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/*    */
/*    */

/*    */
/*    */ public class Mouse
/*    */ implements MouseListener, MouseMotionListener
/*    */ {
    /*  9 */   private static int mouseX = -1;
    /* 10 */   private static int mouseY = -1;
    /* 11 */   private static int mouseB = -1;

    /*    */
/*    */
    public static int getX() {
/* 14 */
        return mouseX;
/*    */
    }

    /*    */
/*    */
    public static int getY() {
/* 18 */
        return mouseY;
/*    */
    }

    /*    */
/*    */
    public static int getButton() {
/* 22 */
        return mouseB;
/*    */
    }

    /*    */
/*    */
    public void mouseDragged(MouseEvent e) {
/* 26 */
        mouseX = e.getX();
/* 27 */
        mouseY = e.getY();
/*    */
    }

    /*    */
/*    */
    public void mouseMoved(MouseEvent e)
/*    */ {
/* 32 */
        mouseX = e.getX();
/* 33 */
        mouseY = e.getY();
/*    */
    }

    /*    */
/*    */
    public void mouseClicked(MouseEvent e)
/*    */ {
/*    */
    }

    /*    */
/*    */
    public void mouseEntered(MouseEvent e)
/*    */ {
/*    */
    }

    /*    */
/*    */
    public void mouseExited(MouseEvent e)
/*    */ {
/*    */
    }

    /*    */
/*    */
    public void mousePressed(MouseEvent e)
/*    */ {
/* 50 */
        mouseB = e.getButton();
/*    */
    }

    /*    */
/*    */
    public void mouseReleased(MouseEvent e)
/*    */ {
/* 55 */
        mouseB = -1;
/*    */
    }
/*    */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.input.Mouse
 * JD-Core Version:    0.6.2
 */