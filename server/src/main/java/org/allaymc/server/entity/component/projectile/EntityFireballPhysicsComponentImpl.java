package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityFireballBaseComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.world.explosion.Explosion;
import org.allaymc.server.component.annotation.Dependency;
import org.joml.Vector3dc;

/**
 * Physics component implementation for fireball (large fireball / ghast fireball) entities.
 * Handles collision with entities and blocks, creating explosions on impact.
 * Unlike small fireballs, large fireballs:
 * - Create explosions with fire
 * - Deal explosion damage in addition to direct hit damage
 * - Can be deflected by players
 *
 * @author daoge_cmd
 */
public class EntityFireballPhysicsComponentImpl extends EntityProjectilePhysicsComponentImpl {

    /**
     * The base direct hit damage dealt by the fireball.
     */
    protected static final int BASE_DAMAGE = 6;

    @Dependency
    protected EntityFireballBaseComponent fireballBaseComponent;

    @Override
    public double getGravity() {
        // Fireballs are not affected by gravity
        return 0;
    }

    @Override
    public double getDragFactorInAir() {
        // No air drag for fireballs
        return 0;
    }

    @Override
    protected void onHitEntity(Entity other, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedNextTick()) {
            return;
        }

        // Deal direct hit damage
        if (other instanceof EntityLiving living) {
            var damage = DamageContainer.projectile(thisEntity, BASE_DAMAGE);
            living.attack(damage);
        }

        // Create explosion at hit position
        createExplosion(hitPos);
        thisEntity.remove();
    }

    @Override
    protected void onHitBlock(Block block, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedNextTick()) {
            return;
        }

        // Create explosion at hit position
        createExplosion(hitPos);
        thisEntity.remove();
    }

    /**
     * Creates an explosion at the specified position.
     *
     * @param pos the position for the explosion
     */
    protected void createExplosion(Vector3dc pos) {
        var dimension = thisEntity.getDimension();
        var power = fireballBaseComponent.getExplosionPower();

        // Create explosion with fire spawning enabled
        var explosion = new Explosion(power, true);
        explosion.setEntity(thisEntity);
        explosion.explode(dimension, pos);
    }
}
