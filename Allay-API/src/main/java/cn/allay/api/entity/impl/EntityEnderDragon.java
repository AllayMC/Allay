package cn.allay.api.entity.impl;

import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;
import cn.allay.api.entity.type.EntityTypeRegistry;
import cn.allay.api.entity.Entity;
import cn.allay.api.data.VanillaEntityId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityEnderDragon extends Entity {
    EntityType<EntityEnderDragon> TYPE = EntityTypeBuilder
            .builder(EntityEnderDragon.class)
            .vanillaEntity(VanillaEntityId.ENDER_DRAGON)
            .addBasicComponents()
            .build().register(EntityTypeRegistry.getRegistry());
}
