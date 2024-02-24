package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityHusk;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityHuskInitializer {
  static void init() {
    EntityTypes.HUSK_TYPE = EntityTypeBuilder
            .builder(EntityHusk.class)
            .vanillaEntity(VanillaEntityId.HUSK)
            .build();
  }
}
