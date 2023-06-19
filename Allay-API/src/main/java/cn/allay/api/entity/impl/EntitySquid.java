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
public interface EntitySquid extends Entity {
  EntityType<EntitySquid> TYPE = EntityTypeBuilder
          .builder(EntitySquid.class)
          .vanillaEntity(VanillaEntityId.SQUID)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());
}
