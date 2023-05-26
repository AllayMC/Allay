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
public interface EntityGuardian extends Entity {
    EntityType<EntityGuardian> TYPE = EntityTypeBuilder
            .builder(EntityGuardian.class)
            .vanillaEntity(VanillaEntityId.GUARDIAN)
            .addBasicComponents()
            .build().register(EntityTypeRegistry.getRegistry());
}
