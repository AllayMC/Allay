package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

/**
 * @author daoge_cmd
 */
public class EntityProjectileBaseComponentImpl extends EntityBaseComponentImpl {

    public EntityProjectileBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    @Override
    public AABBdc getAABB() {
        return new AABBd(-0.125, 0.0, -0.125, 0.125, 0.25, 0.125);
    }
}
