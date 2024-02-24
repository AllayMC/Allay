package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityAllay;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityAllayInitializer {
  static void init() {
    EntityTypes.ALLAY_TYPE = EntityTypeBuilder
            .builder(EntityAllay.class)
            .vanillaEntity(VanillaEntityId.ALLAY)
            .build();
  }
}
