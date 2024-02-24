package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityIceBomb;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityIceBombInitializer {
  static void init() {
    EntityTypes.ICE_BOMB_TYPE = EntityTypeBuilder
            .builder(EntityIceBomb.class)
            .vanillaEntity(VanillaEntityId.ICE_BOMB)
            .build();
  }
}
