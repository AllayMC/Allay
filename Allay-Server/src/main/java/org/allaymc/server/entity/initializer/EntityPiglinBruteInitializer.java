package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityPiglinBrute;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityPiglinBruteInitializer {
  static void init() {
    EntityTypes.PIGLIN_BRUTE_TYPE = EntityTypeBuilder
            .builder(EntityPiglinBrute.class)
            .vanillaEntity(VanillaEntityId.PIGLIN_BRUTE)
            .build();
  }
}
