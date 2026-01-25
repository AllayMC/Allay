package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.entity.EntityInitInfo;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

/**
 * Base component implementation for small fireball entities.
 *
 * @author daoge_cmd
 */
public class EntitySmallFireballBaseComponentImpl extends EntityProjectileBaseComponentImpl {

    public EntitySmallFireballBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    @Override
    public AABBdc getBaseAABB() {
        // Small fireball hitbox is 0.3125 x 0.3125 x 0.3125
        return new AABBd(-0.15625, 0.0, -0.15625, 0.15625, 0.3125, 0.15625);
    }
}
