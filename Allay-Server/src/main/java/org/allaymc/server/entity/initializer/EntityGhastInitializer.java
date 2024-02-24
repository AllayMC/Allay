package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityGhast;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityGhastInitializer {
  static void init() {
    EntityTypes.GHAST_TYPE = EntityTypeBuilder
            .builder(EntityGhast.class)
            .vanillaEntity(VanillaEntityId.GHAST)
            .build();
  }
}
