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
public interface EntityEnderman extends Entity {
  EntityType<EntityEnderman> TYPE = EntityTypeBuilder
          .builder(EntityEnderman.class)
          .vanillaEntity(VanillaEntityId.ENDERMAN)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());
}
