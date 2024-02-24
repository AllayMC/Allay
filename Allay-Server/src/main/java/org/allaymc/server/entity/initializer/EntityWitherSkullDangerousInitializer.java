package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityWitherSkullDangerous;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityWitherSkullDangerousInitializer {
  static void init() {
    EntityTypes.WITHER_SKULL_DANGEROUS_TYPE = EntityTypeBuilder
            .builder(EntityWitherSkullDangerous.class)
            .vanillaEntity(VanillaEntityId.WITHER_SKULL_DANGEROUS)
            .build();
  }
}
