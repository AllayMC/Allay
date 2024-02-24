package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityWolf;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityWolfInitializer {
  static void init() {
    EntityTypes.WOLF_TYPE = EntityTypeBuilder
            .builder(EntityWolf.class)
            .vanillaEntity(VanillaEntityId.WOLF)
            .build();
  }
}
