package org.allaymc.server.entity.component.projectile;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityFireballBaseComponent;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

/**
 * Base component implementation for fireball (large fireball / ghast fireball) entities.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
public class EntityFireballBaseComponentImpl extends EntityProjectileBaseComponentImpl implements EntityFireballBaseComponent {

    /**
     * The explosion power of the fireball (Ghast fireballs have power 1).
     */
    protected static final float EXPLOSION_POWER = 1.0f;

    protected float explosionPower = EXPLOSION_POWER;

    public EntityFireballBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    @Override
    public AABBdc getBaseAABB() {
        // Large fireball hitbox is 1.0 x 1.0 x 1.0
        return new AABBd(-0.5, 0.0, -0.5, 0.5, 1.0, 0.5);
    }
}
