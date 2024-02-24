package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityBoat;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityBoatInitializer {
  static void init() {
    EntityTypes.BOAT_TYPE = EntityTypeBuilder
            .builder(EntityBoat.class)
            .vanillaEntity(VanillaEntityId.BOAT)
            .build();
  }
}
