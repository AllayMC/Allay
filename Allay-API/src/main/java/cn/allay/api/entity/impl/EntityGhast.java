package cn.allay.api.entity.impl;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;
import cn.allay.api.entity.type.EntityTypeRegistry;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityGhast extends Entity {
  EntityType<EntityGhast> TYPE = EntityTypeBuilder
          .builder(EntityGhast.class)
          .vanillaEntity(VanillaEntityId.GHAST)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());
}
