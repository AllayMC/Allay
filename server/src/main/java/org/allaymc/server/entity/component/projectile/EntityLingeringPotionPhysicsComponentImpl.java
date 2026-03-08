package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.data.PotionType;
import org.joml.Vector3d;

/**
 * @author daoge_cmd
 */
public class EntityLingeringPotionPhysicsComponentImpl extends EntityPotionPhysicsComponentImpl {

    protected static final float DURATION_MULTIPLIER = 0.25f;

    @Override
    protected float getDurationMultiplier() {
        return DURATION_MULTIPLIER;
    }

    @Override
    protected void afterSplash(PotionType potionType) {
        spawnAreaEffectCloud(potionType);
    }

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
