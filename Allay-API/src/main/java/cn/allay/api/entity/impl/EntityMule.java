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
public interface EntityMule extends Entity {
    EntityType<EntityMule> TYPE = EntityTypeBuilder
            .builder(EntityMule.class)
            .vanillaEntity(VanillaEntityId.MULE)
            .addBasicComponents()
            .build().register(EntityTypeRegistry.getRegistry());
}
