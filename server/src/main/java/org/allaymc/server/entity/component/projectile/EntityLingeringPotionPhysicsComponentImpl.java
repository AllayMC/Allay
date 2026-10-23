package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.data.PotionType;
import org.joml.Vector3d;

/**
 * {@code EntityLingeringPotionPhysicsComponentImpl} specializes potion impacts
 * with shorter initial effect duration and a lingering cloud. The shared
 * splash path uses a quarter-duration multiplier, then invokes cloud creation
 * only after processing a potion with a nonempty effect list.
 *
 * @author daoge_cmd
 */
public class EntityLingeringPotionPhysicsComponentImpl extends EntityPotionPhysicsComponentImpl {

    /**
     * {@code DURATION_MULTIPLIER} scales noninstant initial splash durations
     * to one quarter before distance attenuation is applied.
     */
    protected static final float DURATION_MULTIPLIER = 0.25f;

    /**
     * Returns the quarter-duration factor for noninstant lingering potion
     * splash effects.
     */
    @Override
    protected float getDurationMultiplier() {
        return DURATION_MULTIPLIER;
    }

    /**
     * Creates an area-effect cloud after the shared effectful splash pass. The
     * parent invokes this hook for nonempty effect lists, so water and other
     * effectless potion types do not reach this path.
     */
    @Override
    protected void afterSplash(PotionType potionType) {
        spawnAreaEffectCloud(potionType);
    }

    /**
     * Creates a cloud at the potion current position with the supplied potion
     * type. The cloud is configured before being queued for addition to the
     * same dimension.
     */
    protected void spawnAreaEffectCloud(PotionType potionType) {
        var dimension = thisEntity.getDimension();
        var location = thisEntity.getLocation();

        var cloud = EntityTypes.AREA_EFFECT_CLOUD.createEntity(
                EntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(new Vector3d(location.x(), location.y(), location.z()))
                        .build()
        );

        cloud.setPotionType(potionType);
        dimension.getEntityManager().addEntity(cloud);
    }
}
