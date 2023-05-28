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
public interface EntityCaveSpider extends Entity {
    EntityType<EntityCaveSpider> TYPE = EntityTypeBuilder
            .builder(EntityCaveSpider.class)
            .vanillaEntity(VanillaEntityId.CAVE_SPIDER)
            .addBasicComponents()
            .build().register(EntityTypeRegistry.getRegistry());
}
