package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.entity.EntityInitInfo;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

/**
 * {@code EntitySmallFireballBaseComponentImpl} supplies the local hitbox for a
 * small fireball entity. The specialized box is slightly wider than the common
 * small-projectile bounds, while inherited base behavior supplies the entity
 * location, state publication, and persistence machinery.
 *
 * @author daoge_cmd
 */
public class EntitySmallFireballBaseComponentImpl extends EntityProjectileBaseComponentImpl {

    /**
     * Creates the small fireball base with its entity initialization context.
     */
    public EntitySmallFireballBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    /**
     * Returns the small fireball local bounding box in block coordinates.
     */
    @Override
    public AABBdc getBaseAABB() {
        return new AABBd(-0.15625, 0.0, -0.15625, 0.15625, 0.3125, 0.15625);
    }
}
