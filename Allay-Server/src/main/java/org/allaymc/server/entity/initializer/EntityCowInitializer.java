package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityCow;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityCowInitializer {
  static void init() {
    EntityTypes.COW_TYPE = EntityTypeBuilder
            .builder(EntityCow.class)
            .vanillaEntity(VanillaEntityId.COW)
            .build();
  }
}
