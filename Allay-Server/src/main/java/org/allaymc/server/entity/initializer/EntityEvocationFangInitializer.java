package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityEvocationFang;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityEvocationFangInitializer {
  static void init() {
    EntityTypes.EVOCATION_FANG_TYPE = EntityTypeBuilder
            .builder(EntityEvocationFang.class)
            .vanillaEntity(VanillaEntityId.EVOCATION_FANG)
            .build();
  }
}
