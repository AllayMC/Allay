package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityEgg;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityEggInitializer {
  static void init() {
    EntityTypes.EGG_TYPE = EntityTypeBuilder
            .builder(EntityEgg.class)
            .vanillaEntity(VanillaEntityId.EGG)
            .build();
  }
}
