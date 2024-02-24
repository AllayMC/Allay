package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityGoat;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityGoatInitializer {
  static void init() {
    EntityTypes.GOAT_TYPE = EntityTypeBuilder
            .builder(EntityGoat.class)
            .vanillaEntity(VanillaEntityId.GOAT)
            .build();
  }
}
