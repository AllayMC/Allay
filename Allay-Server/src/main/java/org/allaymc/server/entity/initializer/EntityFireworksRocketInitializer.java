package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityFireworksRocket;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityFireworksRocketInitializer {
  static void init() {
    EntityTypes.FIREWORKS_ROCKET_TYPE = EntityTypeBuilder
            .builder(EntityFireworksRocket.class)
            .vanillaEntity(VanillaEntityId.FIREWORKS_ROCKET)
            .build();
  }
}
