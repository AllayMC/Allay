package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityProjectileComponent;
import org.allaymc.api.world.explosion.WindExplosion;
import org.allaymc.server.component.annotation.Dependency;
import org.joml.Vector3dc;

/**
 * {@code EntityWindChargePhysicsComponentImpl} resolves player-style wind
 * charge impacts into a wind burst. Free-flight motion uses zero gravity and
 * zero air drag, while accepted block or entity hits configure source and
 * shooter attribution before invoking the burst and removing the projectile.
 *
 * @author daoge_cmd
 */
public class EntityWindChargePhysicsComponentImpl extends EntityProjectilePhysicsComponentImpl {

    /**
     * Injected retained shooter reference component.
     */
    @Dependency
    protected EntityProjectileComponent projectileComponent;

    /**
     * Returns zero downward acceleration for freely flying wind charges.
     */
    @Override
    public double getGravity() {
        return 0.0;
    }

    /**
     * Returns zero air drag so free-flight wind-charge motion is retained.
     */
    @Override
    public double getDragFactorInAir() {
        return 0.0;
    }

    /**
     * Triggers the configured wind burst at a block impact and requests
     * removal. An already queued removal suppresses the burst, and the burst
     * factory supplies the player or subclass-specific variant.
     */
    @Override
    protected void onHitBlock(Block block, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedLater()) {
            return;
        }

        triggerWindExplosion(hitPos);
        thisEntity.remove();
    }

    /**
     * Triggers the configured wind burst at an entity impact and requests
     * removal. An already queued removal suppresses the burst, and direct
     * damage is left to the configured burst behavior.
     */
    @Override
    protected void onHitEntity(Entity entity, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedLater()) {
            return;
        }

        triggerWindExplosion(hitPos);
        thisEntity.remove();
    }

    /**
     * Creates a wind burst and assigns this projectile and its current shooter
     * as attribution. The burst executes in the projectile dimension at {@code
     * hitPos}, while impact callers own projectile removal.
     */
    protected void triggerWindExplosion(Vector3dc hitPos) {
        var explosion = createWindExplosion();
        explosion.setSource(thisEntity);
        explosion.setShooter(projectileComponent.getShooter());
        explosion.explode(thisEntity.getDimension(), hitPos);
    }

    /**
     * Returns a new player-style wind burst for impact processing. Subclasses
     * can replace this factory result to change radius, strengths, effects, or
     * shooter self-knockback without replacing both impact handlers.
     */
    protected WindExplosion createWindExplosion() {
        return WindExplosion.playerWindCharge();
    }
}
