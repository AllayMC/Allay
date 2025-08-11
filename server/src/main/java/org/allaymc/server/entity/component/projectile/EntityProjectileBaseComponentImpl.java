package org.allaymc.server.entity.component.projectile;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

/**
 * @author daoge_cmd
 */
public class EntityProjectileBaseComponentImpl extends EntityBaseComponentImpl {

    protected static final int MAX_AGE = 6000;

    @Getter
    @Setter
    protected Entity shooter;
    protected int age;

    public EntityProjectileBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    @Override
    public AABBdc getAABB() {
        return new AABBd(-0.125, 0.0, -0.125, 0.125, 0.25, 0.125);
    }
}
