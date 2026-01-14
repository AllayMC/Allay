package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityProjectileComponent;
import org.allaymc.api.world.explosion.WindExplosion;
import org.allaymc.server.component.annotation.Dependency;
import org.joml.Vector3dc;

/**
 * Physics component for player-thrown wind charge projectiles.
 * <p>
 * Wind charges have:
 * <ul>
 *     <li>Very low gravity (almost none)</li>
 *     <li>No air drag</li>
 *     <li>Explode on impact with blocks or entities</li>
 *     <li>Create wind explosions that knockback entities without damage</li>
 * </ul>
 *
 * @author daoge_cmd
 */
public class EntityWindChargePhysicsComponentImpl extends EntityProjectilePhysicsComponentImpl {

    @Dependency
    protected EntityProjectileComponent projectileComponent;

    @Override
    public double getGravity() {
        // Wind charges have no gravity
        return 0.0;
    }

    @Override
    public double getDragFactorInAir() {
        // No air drag
        return 0.0;
    }

    @Override
    protected void onHitBlock(Block block, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedNextTick()) {
            return;
        }

        triggerWindExplosion(hitPos);
        thisEntity.remove();
    }

    @Override
    protected void onHitEntity(Entity entity, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedNextTick()) {
            return;
        }

        triggerWindExplosion(hitPos);
        thisEntity.remove();
    }

    /**
     * Triggers the wind explosion at the hit position.
     * Can be overridden by subclasses to customize explosion behavior.
     *
     * @param hitPos the position where the projectile hit
     */
    protected void triggerWindExplosion(Vector3dc hitPos) {
        var explosion = createWindExplosion();
        explosion.setSource(thisEntity);
        explosion.setShooter(projectileComponent.getShooter());
        explosion.explode(thisEntity.getDimension(), hitPos);
    }

    /**
     * Creates the wind explosion instance.
     * Override this method to customize the explosion parameters.
     *
     * @return the configured WindExplosion
     */
    protected WindExplosion createWindExplosion() {
        return WindExplosion.playerWindCharge();
    }
}
