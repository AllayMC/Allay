package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntitySmallFireball;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntitySmallFireballInitializer {
  static void init() {
    EntityTypes.SMALL_FIREBALL_TYPE = EntityTypeBuilder
            .builder(EntitySmallFireball.class)
            .vanillaEntity(VanillaEntityId.SMALL_FIREBALL)
            .build();
  }
}
