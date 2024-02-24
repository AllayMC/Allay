package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityWither;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityWitherInitializer {
  static void init() {
    EntityTypes.WITHER_TYPE = EntityTypeBuilder
            .builder(EntityWither.class)
            .vanillaEntity(VanillaEntityId.WITHER)
            .build();
  }
}
