package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityZombieVillager extends Entity {
  EntityType<EntityZombieVillager> ZOMBIE_VILLAGER_TYPE = EntityTypeBuilder
          .builder(EntityZombieVillager.class)
          .vanillaEntity(VanillaEntityId.ZOMBIE_VILLAGER)
          .build();
}
