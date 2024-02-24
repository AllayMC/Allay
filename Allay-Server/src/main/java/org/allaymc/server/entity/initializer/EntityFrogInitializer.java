package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityFrog;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityFrogInitializer {
  static void init() {
    EntityTypes.FROG_TYPE = EntityTypeBuilder
            .builder(EntityFrog.class)
            .vanillaEntity(VanillaEntityId.FROG)
            .build();
  }
}
