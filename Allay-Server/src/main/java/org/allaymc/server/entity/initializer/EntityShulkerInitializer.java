package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityShulker;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityShulkerInitializer {
  static void init() {
    EntityTypes.SHULKER_TYPE = EntityTypeBuilder
            .builder(EntityShulker.class)
            .vanillaEntity(VanillaEntityId.SHULKER)
            .build();
  }
}
