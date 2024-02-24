package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityDolphin;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityDolphinInitializer {
  static void init() {
    EntityTypes.DOLPHIN_TYPE = EntityTypeBuilder
            .builder(EntityDolphin.class)
            .vanillaEntity(VanillaEntityId.DOLPHIN)
            .build();
  }
}
