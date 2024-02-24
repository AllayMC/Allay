package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityZombie;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityZombieInitializer {
  static void init() {
    EntityTypes.ZOMBIE_TYPE = EntityTypeBuilder
            .builder(EntityZombie.class)
            .vanillaEntity(VanillaEntityId.ZOMBIE)
            .build();
  }
}
