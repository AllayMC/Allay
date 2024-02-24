package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityHorse;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityHorseInitializer {
  static void init() {
    EntityTypes.HORSE_TYPE = EntityTypeBuilder
            .builder(EntityHorse.class)
            .vanillaEntity(VanillaEntityId.HORSE)
            .build();
  }
}
