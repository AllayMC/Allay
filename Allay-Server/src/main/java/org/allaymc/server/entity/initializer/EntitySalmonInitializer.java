package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntitySalmon;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntitySalmonInitializer {
  static void init() {
    EntityTypes.SALMON_TYPE = EntityTypeBuilder
            .builder(EntitySalmon.class)
            .vanillaEntity(VanillaEntityId.SALMON)
            .build();
  }
}
