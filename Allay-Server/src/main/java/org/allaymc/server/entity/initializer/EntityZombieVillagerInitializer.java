package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityZombieVillager;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityZombieVillagerInitializer {
  static void init() {
    EntityTypes.ZOMBIE_VILLAGER_TYPE = EntityTypeBuilder
            .builder(EntityZombieVillager.class)
            .vanillaEntity(VanillaEntityId.ZOMBIE_VILLAGER)
            .build();
  }
}
