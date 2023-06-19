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
public interface EntityVex extends Entity {
  EntityType<EntityVex> TYPE = EntityTypeBuilder
          .builder(EntityVex.class)
          .vanillaEntity(VanillaEntityId.VEX)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());
}
