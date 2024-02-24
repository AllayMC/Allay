package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityTadpole;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityTadpoleInitializer {
  static void init() {
    EntityTypes.TADPOLE_TYPE = EntityTypeBuilder
            .builder(EntityTadpole.class)
            .vanillaEntity(VanillaEntityId.TADPOLE)
            .build();
  }
}
