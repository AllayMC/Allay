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
public interface EntityDonkey extends Entity {
  EntityType<EntityDonkey> TYPE = EntityTypeBuilder
          .builder(EntityDonkey.class)
          .vanillaEntity(VanillaEntityId.DONKEY)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());
}
