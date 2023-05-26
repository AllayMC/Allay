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
public interface EntityFox extends Entity {
    EntityType<EntityFox> TYPE = EntityTypeBuilder
            .builder(EntityFox.class)
            .vanillaEntity(VanillaEntityId.FOX)
            .addBasicComponents()
            .build().register(EntityTypeRegistry.getRegistry());
}
