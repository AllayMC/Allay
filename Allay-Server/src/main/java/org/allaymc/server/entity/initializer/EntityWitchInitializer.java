package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityWitch;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityWitchInitializer {
  static void init() {
    EntityTypes.WITCH_TYPE = EntityTypeBuilder
            .builder(EntityWitch.class)
            .vanillaEntity(VanillaEntityId.WITCH)
            .build();
  }
}
