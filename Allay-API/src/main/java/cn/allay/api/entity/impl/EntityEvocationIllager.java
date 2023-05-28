package cn.allay.api.entity.impl;

import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;
import cn.allay.api.entity.type.EntityTypeRegistry;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.data.VanillaEntityId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityEvocationIllager extends Entity {
    EntityType<EntityEvocationIllager> TYPE = EntityTypeBuilder
            .builder(EntityEvocationIllager.class)
            .vanillaEntity(VanillaEntityId.EVOCATION_ILLAGER)
            .addBasicComponents()
            .build().register(EntityTypeRegistry.getRegistry());
}
