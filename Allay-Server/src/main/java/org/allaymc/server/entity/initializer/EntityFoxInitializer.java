package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityFox;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityFoxInitializer {
  static void init() {
    EntityTypes.FOX_TYPE = EntityTypeBuilder
            .builder(EntityFox.class)
            .vanillaEntity(VanillaEntityId.FOX)
            .build();
  }
}
