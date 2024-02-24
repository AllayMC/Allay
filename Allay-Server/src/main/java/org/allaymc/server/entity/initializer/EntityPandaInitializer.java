package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityPanda;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityPandaInitializer {
  static void init() {
    EntityTypes.PANDA_TYPE = EntityTypeBuilder
            .builder(EntityPanda.class)
            .vanillaEntity(VanillaEntityId.PANDA)
            .build();
  }
}
