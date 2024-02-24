package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntitySnowball;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntitySnowballInitializer {
  static void init() {
    EntityTypes.SNOWBALL_TYPE = EntityTypeBuilder
            .builder(EntitySnowball.class)
            .vanillaEntity(VanillaEntityId.SNOWBALL)
            .build();
  }
}
