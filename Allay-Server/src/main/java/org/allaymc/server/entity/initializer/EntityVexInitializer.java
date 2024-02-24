package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityVex;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityVexInitializer {
  static void init() {
    EntityTypes.VEX_TYPE = EntityTypeBuilder
            .builder(EntityVex.class)
            .vanillaEntity(VanillaEntityId.VEX)
            .build();
  }
}
