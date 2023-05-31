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
public interface EntityEndermite extends Entity {
    EntityType<EntityEndermite> TYPE = EntityTypeBuilder
            .builder(EntityEndermite.class)
            .vanillaEntity(VanillaEntityId.ENDERMITE)
            .addBasicComponents()
            .build().register(EntityTypeRegistry.getRegistry());
}
