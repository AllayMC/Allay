package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityEnderman;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityEndermanInitializer {
  static void init() {
    EntityTypes.ENDERMAN_TYPE = EntityTypeBuilder
            .builder(EntityEnderman.class)
            .vanillaEntity(VanillaEntityId.ENDERMAN)
            .build();
  }
}
