package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityBlaze;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityBlazeInitializer {
  static void init() {
    EntityTypes.BLAZE_TYPE = EntityTypeBuilder
            .builder(EntityBlaze.class)
            .vanillaEntity(VanillaEntityId.BLAZE)
            .build();
  }
}
