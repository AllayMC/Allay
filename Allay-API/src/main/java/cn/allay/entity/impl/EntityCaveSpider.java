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
public interface EntityCaveSpider extends Entity {
    EntityType<EntityCaveSpider> TYPE = EntityTypeBuilder
            .builder(EntityCaveSpider.class)
            .vanillaEntity(VanillaEntityId.CAVE_SPIDER)
            .addBasicComponents()
            .build().register(EntityTypeRegistry.getRegistry());
}
