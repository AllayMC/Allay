package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityZombiePigman;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityZombiePigmanInitializer {
  static void init() {
    EntityTypes.ZOMBIE_PIGMAN_TYPE = EntityTypeBuilder
            .builder(EntityZombiePigman.class)
            .vanillaEntity(VanillaEntityId.ZOMBIE_PIGMAN)
            .build();
  }
}
