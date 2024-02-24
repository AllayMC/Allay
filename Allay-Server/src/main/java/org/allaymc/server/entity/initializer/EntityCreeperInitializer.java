package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityCreeper;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityCreeperInitializer {
  static void init() {
    EntityTypes.CREEPER_TYPE = EntityTypeBuilder
            .builder(EntityCreeper.class)
            .vanillaEntity(VanillaEntityId.CREEPER)
            .build();
  }
}
