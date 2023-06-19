package cn.allay.api.entity.impl;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;
import cn.allay.api.entity.type.EntityTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntitySnowGolem extends Entity {
  EntityType<EntitySnowGolem> TYPE = EntityTypeBuilder
          .builder(EntitySnowGolem.class)
          .vanillaEntity(VanillaEntityId.SNOW_GOLEM)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());
}
