package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityPufferfish;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityPufferfishInitializer {
  static void init() {
    EntityTypes.PUFFERFISH_TYPE = EntityTypeBuilder
            .builder(EntityPufferfish.class)
            .vanillaEntity(VanillaEntityId.PUFFERFISH)
            .build();
  }
}
