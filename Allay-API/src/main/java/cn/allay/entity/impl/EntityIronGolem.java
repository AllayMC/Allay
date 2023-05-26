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
public interface EntityIronGolem extends Entity {
    EntityType<EntityIronGolem> TYPE = EntityTypeBuilder
            .builder(EntityIronGolem.class)
            .vanillaEntity(VanillaEntityId.IRON_GOLEM)
            .addBasicComponents()
            .build().register(EntityTypeRegistry.getRegistry());
}
