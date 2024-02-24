package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityThrownTrident;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityThrownTridentInitializer {
  static void init() {
    EntityTypes.THROWN_TRIDENT_TYPE = EntityTypeBuilder
            .builder(EntityThrownTrident.class)
            .vanillaEntity(VanillaEntityId.THROWN_TRIDENT)
            .build();
  }
}
