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
public interface EntityGlowSquid extends Entity {
  EntityType<EntityGlowSquid> TYPE = EntityTypeBuilder
          .builder(EntityGlowSquid.class)
          .vanillaEntity(VanillaEntityId.GLOW_SQUID)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());
}
