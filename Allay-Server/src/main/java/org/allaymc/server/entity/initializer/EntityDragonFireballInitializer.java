package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityDragonFireball;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityDragonFireballInitializer {
  static void init() {
    EntityTypes.DRAGON_FIREBALL_TYPE = EntityTypeBuilder
            .builder(EntityDragonFireball.class)
            .vanillaEntity(VanillaEntityId.DRAGON_FIREBALL)
            .build();
  }
}
