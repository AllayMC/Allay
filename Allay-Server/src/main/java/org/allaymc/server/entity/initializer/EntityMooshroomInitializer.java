package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityMooshroom;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityMooshroomInitializer {
  static void init() {
    EntityTypes.MOOSHROOM_TYPE = EntityTypeBuilder
            .builder(EntityMooshroom.class)
            .vanillaEntity(VanillaEntityId.MOOSHROOM)
            .build();
  }
}
