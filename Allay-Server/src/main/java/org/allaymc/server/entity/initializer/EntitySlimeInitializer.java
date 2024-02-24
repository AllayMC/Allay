package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntitySlime;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntitySlimeInitializer {
  static void init() {
    EntityTypes.SLIME_TYPE = EntityTypeBuilder
            .builder(EntitySlime.class)
            .vanillaEntity(VanillaEntityId.SLIME)
            .build();
  }
}
