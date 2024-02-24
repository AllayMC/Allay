package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityEnderPearl;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityEnderPearlInitializer {
  static void init() {
    EntityTypes.ENDER_PEARL_TYPE = EntityTypeBuilder
            .builder(EntityEnderPearl.class)
            .vanillaEntity(VanillaEntityId.ENDER_PEARL)
            .build();
  }
}
