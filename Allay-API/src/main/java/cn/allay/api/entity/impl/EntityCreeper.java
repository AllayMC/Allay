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
public interface EntityCreeper extends Entity {
  EntityType<EntityCreeper> TYPE = EntityTypeBuilder
          .builder(EntityCreeper.class)
          .vanillaEntity(VanillaEntityId.CREEPER)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());
}
