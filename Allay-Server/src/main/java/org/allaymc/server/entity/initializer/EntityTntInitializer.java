package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityTnt;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityTntInitializer {
  static void init() {
    EntityTypes.TNT_TYPE = EntityTypeBuilder
            .builder(EntityTnt.class)
            .vanillaEntity(VanillaEntityId.TNT)
            .build();
  }
}
