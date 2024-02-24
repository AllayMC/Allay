package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityDrowned;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityDrownedInitializer {
  static void init() {
    EntityTypes.DROWNED_TYPE = EntityTypeBuilder
            .builder(EntityDrowned.class)
            .vanillaEntity(VanillaEntityId.DROWNED)
            .build();
  }
}
