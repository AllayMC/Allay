package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityWitherSkull;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityWitherSkullInitializer {
  static void init() {
    EntityTypes.WITHER_SKULL_TYPE = EntityTypeBuilder
            .builder(EntityWitherSkull.class)
            .vanillaEntity(VanillaEntityId.WITHER_SKULL)
            .build();
  }
}
