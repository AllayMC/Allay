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
public interface EntitySnowGolem extends Entity {
    EntityType<EntitySnowGolem> TYPE = EntityTypeBuilder
            .builder(EntitySnowGolem.class)
            .vanillaEntity(VanillaEntityId.SNOW_GOLEM)
            .addBasicComponents()
            .build().register(EntityTypeRegistry.getRegistry());
}
