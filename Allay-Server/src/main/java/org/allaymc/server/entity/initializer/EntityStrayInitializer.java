package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityStray;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityStrayInitializer {
  static void init() {
    EntityTypes.STRAY_TYPE = EntityTypeBuilder
            .builder(EntityStray.class)
            .vanillaEntity(VanillaEntityId.STRAY)
            .build();
  }
}
