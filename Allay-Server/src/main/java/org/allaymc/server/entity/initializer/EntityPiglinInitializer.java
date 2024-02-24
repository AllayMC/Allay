package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityPiglin;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityPiglinInitializer {
  static void init() {
    EntityTypes.PIGLIN_TYPE = EntityTypeBuilder
            .builder(EntityPiglin.class)
            .vanillaEntity(VanillaEntityId.PIGLIN)
            .build();
  }
}
