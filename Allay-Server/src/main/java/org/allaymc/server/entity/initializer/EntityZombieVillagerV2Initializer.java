package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityZombieVillagerV2;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityZombieVillagerV2Initializer {
  static void init() {
    EntityTypes.ZOMBIE_VILLAGER_V2_TYPE = EntityTypeBuilder
            .builder(EntityZombieVillagerV2.class)
            .vanillaEntity(VanillaEntityId.ZOMBIE_VILLAGER_V2)
            .build();
  }
}
