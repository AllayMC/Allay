package cn.allay.api.entity.impl;

import cn.allay.api.entity.Entity;
import cn.allay.api.entity.data.VanillaEntityId;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;
import cn.allay.api.entity.type.EntityTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityStray extends Entity {
    EntityType<EntityStray> TYPE = EntityTypeBuilder
            .builder(EntityStray.class)
            .vanillaEntity(VanillaEntityId.STRAY)
            .addBasicComponents()
            .build().register(EntityTypeRegistry.getRegistry());
}
