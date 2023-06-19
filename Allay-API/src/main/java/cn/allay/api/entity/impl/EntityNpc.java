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
public interface EntityNpc extends Entity {
  EntityType<EntityNpc> TYPE = EntityTypeBuilder
          .builder(EntityNpc.class)
          .vanillaEntity(VanillaEntityId.NPC)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());
}
