package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.world.explosion.WindExplosion;

/**
 * Physics component for breeze wind charge projectiles.
 * <p>
 * Breeze wind charges differ from player wind charges:
 * <ul>
 *     <li>Larger knockback radius (3.0 vs 2.0)</li>
 *     <li>90% reduced knockback strength</li>
 *     <li>Different particle effect (BREEZE_WIND_EXPLOSION)</li>
 *     <li>Different sound effect (BREEZE_WIND_CHARGE_BURST)</li>
 *     <li>No self-knockback applied to the breeze</li>
 * </ul>
 *
 * @author daoge_cmd
 */
public class EntityBreezeWindChargePhysicsComponentImpl extends EntityWindChargePhysicsComponentImpl {

    @Override
    protected WindExplosion createWindExplosion() {
        return WindExplosion.breezeWindCharge();
    }
}
