package cn.allay.entity.impl;

import cn.allay.entity.Entity;
import cn.allay.entity.data.VanillaEntityId;
import cn.allay.entity.type.EntityType;
import cn.allay.entity.type.EntityTypeBuilder;
import cn.allay.entity.type.EntityTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntitySkeletonHorse extends Entity {
    EntityType<EntitySkeletonHorse> TYPE = EntityTypeBuilder
            .builder(EntitySkeletonHorse.class)
            .vanillaEntity(VanillaEntityId.SKELETON_HORSE)
            .addBasicComponents()
            .build().register(EntityTypeRegistry.getRegistry());
}
