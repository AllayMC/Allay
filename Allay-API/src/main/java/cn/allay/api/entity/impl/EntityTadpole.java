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
public interface EntityTadpole extends Entity {
    EntityType<EntityTadpole> TYPE = EntityTypeBuilder
            .builder(EntityTadpole.class)
            .vanillaEntity(VanillaEntityId.TADPOLE)
            .addBasicComponents()
            .build().register(EntityTypeRegistry.getRegistry());
}
