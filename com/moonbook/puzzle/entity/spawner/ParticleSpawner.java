/*    */
package com.moonbook.puzzle.entity.spawner;
/*    */ 
/*    */

import com.moonbook.puzzle.entity.particle.Particle;
import com.moonbook.puzzle.entity.particle.ParticleBlue;
import com.moonbook.puzzle.entity.projectile.Projectile;
import com.moonbook.puzzle.level.Level;

/*    */
/*    */
/*    */

/*    */
/*    */ public class ParticleSpawner extends Spawner
/*    */ {
    /*    */   private int life;

    /*    */
/*    */
    public ParticleSpawner(int x, int y, int life, int amount, Level level, Projectile.AttType type)
/*    */ {
/* 13 */
        super(x, y, Spawner.Type.PARTICLE, amount, level);
/* 14 */
        this.life = life;
/*    */ 
/* 16 */
        if (type == Projectile.AttType.BASIC) {
/* 17 */
            for (int i = 0; i < amount; i++) {
/* 18 */
                level.add(new Particle(x, y, life));
/*    */
            }
/*    */
        }
/*    */ 
/* 22 */
        if (type == Projectile.AttType.FROST)
/* 23 */ for (int i = 0; i < amount; i++)
/* 24 */
            level.add(new ParticleBlue(x, y, life));
/*    */
    }
/*    */
}

/* Location:           C:\Users\moonbook\Desktop\Face Of The Moon\FoTM v1.1.jar
 * Qualified Name:     com.moonbook.puzzle.entity.spawner.ParticleSpawner
 * JD-Core Version:    0.6.2
 */