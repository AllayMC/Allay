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
 * {@code EntityFireballPhysicsComponentImpl} resolves explosive fireball
 * impacts into direct damage and a fiery explosion. Air updates use no gravity
 * or air drag, and both entity and block impacts execute the configured
 * explosion before requesting projectile removal.
 *
 * @author daoge_cmd
 */
public class EntityFireballPhysicsComponentImpl extends EntityProjectilePhysicsComponentImpl {

    /**
     * {@code BASE_DAMAGE} supplies the six direct damage points offered to
     * living targets before the impact explosion.
     */
    protected static final int BASE_DAMAGE = 6;

    /**
     * Injected configurable explosion power component.
     */
    @Dependency
    protected EntityFireballBaseComponent fireballBaseComponent;

    /**
     * Returns zero downward acceleration for freely flying explosive
     * fireballs.
     */
    @Override
    public double getGravity() {
        return 0;
    }

    /**
     * Returns zero air drag so free-flight fireball motion is retained.
     */
    @Override
    public double getDragFactorInAir() {
        return 0;
    }

    /**
     * Offers direct projectile damage to a living target before creating the
     * impact explosion. The explosion and removal still proceed when that
     * attack is rejected, unless removal was already queued before this
     * callback.
     */
    @Override
    protected void onHitEntity(Entity other, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedLater()) {
            return;
        }

        if (other instanceof EntityLiving living) {
            var damage = DamageContainer.projectile(thisEntity, BASE_DAMAGE);
            living.attack(damage);
        }

        createExplosion(hitPos);
        thisEntity.remove();
    }

    /**
     * Creates the impact explosion and then requests fireball removal. An
     * already queued removal skips this callback, while the hit position
     * supplies the explosion origin rather than the block coordinates.
     */
    @Override
    protected void onHitBlock(Block block, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedLater()) {
            return;
        }

        createExplosion(hitPos);
        thisEntity.remove();
    }

    /**
     * Executes a new fiery explosion using the current configured fireball
     * power. The explosion retains this projectile as its source, and this
     * helper does not dispatch an {@code EntityExplodeEvent} or remove the
     * projectile.
     */
    protected void createExplosion(Vector3dc pos) {
        var dimension = thisEntity.getDimension();
        var power = fireballBaseComponent.getExplosionPower();

        var explosion = new Explosion(power, true);
        explosion.setEntity(thisEntity);
        explosion.explode(dimension, pos);
    }
}
