package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityZombieHorse;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityZombieHorseInitializer {
  static void init() {
    EntityTypes.ZOMBIE_HORSE_TYPE = EntityTypeBuilder
            .builder(EntityZombieHorse.class)
            .vanillaEntity(VanillaEntityId.ZOMBIE_HORSE)
            .build();
  }
}
