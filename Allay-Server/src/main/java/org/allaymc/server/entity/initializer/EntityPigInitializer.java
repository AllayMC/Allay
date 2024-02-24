package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityPig;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityPigInitializer {
  static void init() {
    EntityTypes.PIG_TYPE = EntityTypeBuilder
            .builder(EntityPig.class)
            .vanillaEntity(VanillaEntityId.PIG)
            .build();
  }
}
