package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityFireball;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityFireballInitializer {
  static void init() {
    EntityTypes.FIREBALL_TYPE = EntityTypeBuilder
            .builder(EntityFireball.class)
            .vanillaEntity(VanillaEntityId.FIREBALL)
            .build();
  }
}
