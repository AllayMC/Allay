package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityHoglin;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityHoglinInitializer {
  static void init() {
    EntityTypes.HOGLIN_TYPE = EntityTypeBuilder
            .builder(EntityHoglin.class)
            .vanillaEntity(VanillaEntityId.HOGLIN)
            .build();
  }
}
