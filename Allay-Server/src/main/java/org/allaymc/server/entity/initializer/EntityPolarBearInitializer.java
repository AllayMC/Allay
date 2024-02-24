package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityPolarBear;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityPolarBearInitializer {
  static void init() {
    EntityTypes.POLAR_BEAR_TYPE = EntityTypeBuilder
            .builder(EntityPolarBear.class)
            .vanillaEntity(VanillaEntityId.POLAR_BEAR)
            .build();
  }
}
