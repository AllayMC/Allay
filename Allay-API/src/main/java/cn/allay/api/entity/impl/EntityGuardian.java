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
public interface EntityGuardian extends Entity {
    EntityType<EntityGuardian> TYPE = EntityTypeBuilder
            .builder(EntityGuardian.class)
            .vanillaEntity(VanillaEntityId.GUARDIAN)
            .addBasicComponents()
            .build().register(EntityTypeRegistry.getRegistry());
}
