/*    */
package com.moonbook.puzzle.entity.spawner;
/*    */ 
/*    */

import com.moonbook.puzzle.entity.Entity;
import com.moonbook.puzzle.level.Level;

/*    */

/*    */
/*    */ public abstract class Spawner extends Entity
/*    */ {
    /*    */   private Type type;

    /*    */
/*    */
    public Spawner(int x, int y, Type type, int amount, Level level)
/*    */ {
/* 15 */
        this.x = x;
/* 16 */
        this.y = y;
/* 17 */
        this.type = type;
/*    */
    }

    /*    */
/*    */   public static enum Type
/*    */ {
        /*  9 */     MOB, PARTICLE;
/*    */
    }
/*    */
}
