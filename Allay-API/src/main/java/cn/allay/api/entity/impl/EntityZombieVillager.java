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
public interface EntityZombieVillager extends Entity {
  EntityType<EntityZombieVillager> TYPE = EntityTypeBuilder
          .builder(EntityZombieVillager.class)
          .vanillaEntity(VanillaEntityId.ZOMBIE_VILLAGER)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());
}
