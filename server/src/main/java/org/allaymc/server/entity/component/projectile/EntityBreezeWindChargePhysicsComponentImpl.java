package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.world.explosion.WindExplosion;

/**
 * {@code EntityBreezeWindChargePhysicsComponentImpl} selects the breeze
 * variant of wind-charge impact physics. The inherited impact path creates a
 * wind burst, while this variant uses a larger radius, ninety-percent motion
 * strengths, breeze effects, and disabled shooter self-knockback.
 *
 * @author daoge_cmd
 */
public class EntityBreezeWindChargePhysicsComponentImpl extends EntityWindChargePhysicsComponentImpl {

    /**
     * Returns a new breeze-style wind burst with a three-block radius. The
     * burst uses ninety percent of player-style strengths and excludes the
     * shooter from its knockback response.
     */
    @Override
    protected WindExplosion createWindExplosion() {
        return WindExplosion.breezeWindCharge();
    }
}
