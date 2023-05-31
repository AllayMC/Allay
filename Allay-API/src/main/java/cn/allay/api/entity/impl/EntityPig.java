package cn.allay.api.entity.impl;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;
import cn.allay.api.entity.type.EntityTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityPig extends Entity {
    EntityType<EntityPig> TYPE = EntityTypeBuilder
            .builder(EntityPig.class)
            .vanillaEntity(VanillaEntityId.PIG)
            .addBasicComponents()
            .build().register(EntityTypeRegistry.getRegistry());
}
