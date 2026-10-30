package org.allaymc.server.entity.component.projectile;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityFireballBaseComponent;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

/**
 * {@code EntityFireballBaseComponentImpl} represents an explosive fireball
 * with configurable blast power. The component starts with power one and
 * exposes a one-block local hitbox, while inherited projectile entity behavior
 * supplies the common location and persistence contracts.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
public class EntityFireballBaseComponentImpl extends EntityProjectileBaseComponentImpl implements EntityFireballBaseComponent {

    /**
     * {@code EXPLOSION_POWER} initializes the mutable blast strength used when
     * explosive fireballs create their impact explosion.
     */
    protected static final float EXPLOSION_POWER = 1.0f;

    /**
     * Current explosive fireball blast strength.
     */
    protected float explosionPower = EXPLOSION_POWER;

    /**
     * Creates the fireball base with the supplied entity initialization
     * context.
     */
    public EntityFireballBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    /**
     * Returns the explosive fireball local one-block bounding box in blocks.
     */
    @Override
    public AABBdc getBaseAABB() {
        return new AABBd(-0.5, 0.0, -0.5, 0.5, 1.0, 0.5);
    }
}
