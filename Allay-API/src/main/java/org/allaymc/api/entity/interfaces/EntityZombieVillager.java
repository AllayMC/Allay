package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

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
