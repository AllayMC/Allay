package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityChestBoat;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityChestBoatInitializer {
  static void init() {
    EntityTypes.CHEST_BOAT_TYPE = EntityTypeBuilder
            .builder(EntityChestBoat.class)
            .vanillaEntity(VanillaEntityId.CHEST_BOAT)
            .build();
  }
}
