package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityEnderDragon;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityEnderDragonInitializer {
  static void init() {
    EntityTypes.ENDER_DRAGON_TYPE = EntityTypeBuilder
            .builder(EntityEnderDragon.class)
            .vanillaEntity(VanillaEntityId.ENDER_DRAGON)
            .build();
  }
}
