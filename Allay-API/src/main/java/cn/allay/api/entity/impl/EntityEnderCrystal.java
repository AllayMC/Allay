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
public interface EntityEnderCrystal extends Entity {
  EntityType<EntityEnderCrystal> TYPE = EntityTypeBuilder
          .builder(EntityEnderCrystal.class)
          .vanillaEntity(VanillaEntityId.ENDER_CRYSTAL)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());
}
