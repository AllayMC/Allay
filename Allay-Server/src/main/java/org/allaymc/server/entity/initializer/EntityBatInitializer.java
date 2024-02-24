package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityBat;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityBatInitializer {
  static void init() {
    EntityTypes.BAT_TYPE = EntityTypeBuilder
            .builder(EntityBat.class)
            .vanillaEntity(VanillaEntityId.BAT)
            .build();
  }
}
