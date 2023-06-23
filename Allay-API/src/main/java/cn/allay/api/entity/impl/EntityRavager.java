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
public interface EntityRavager extends Entity {
  EntityType<EntityRavager> TYPE = EntityTypeBuilder
          .builder(EntityRavager.class)
          .vanillaEntity(VanillaEntityId.RAVAGER)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());
}
