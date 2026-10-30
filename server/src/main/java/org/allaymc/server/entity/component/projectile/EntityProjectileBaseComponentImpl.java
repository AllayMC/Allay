package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

/**
 * {@code EntityProjectileBaseComponentImpl} supplies common entity-base
 * behavior for small projectile entities. The default local bounds form a
 * quarter-block cube centered horizontally at the entity position, while
 * specialized projectile bases can override those dimensions.
 *
 * @author daoge_cmd
 */
public class EntityProjectileBaseComponentImpl extends EntityBaseComponentImpl {

    /**
     * Creates the projectile base with the supplied entity initialization
     * context.
     */
    public EntityProjectileBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    /**
     * Returns the default projectile local quarter-block bounding box in
     * blocks.
     */
    @Override
    public AABBdc getBaseAABB() {
        return new AABBd(-0.125, 0.0, -0.125, 0.125, 0.25, 0.125);
    }
}
