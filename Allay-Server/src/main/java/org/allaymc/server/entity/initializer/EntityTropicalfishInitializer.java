package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityTropicalfish;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityTropicalfishInitializer {
  static void init() {
    EntityTypes.TROPICALFISH_TYPE = EntityTypeBuilder
            .builder(EntityTropicalfish.class)
            .vanillaEntity(VanillaEntityId.TROPICALFISH)
            .build();
  }
}
