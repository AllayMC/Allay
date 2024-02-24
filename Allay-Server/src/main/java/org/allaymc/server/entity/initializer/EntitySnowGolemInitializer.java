package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntitySnowGolem;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntitySnowGolemInitializer {
  static void init() {
    EntityTypes.SNOW_GOLEM_TYPE = EntityTypeBuilder
            .builder(EntitySnowGolem.class)
            .vanillaEntity(VanillaEntityId.SNOW_GOLEM)
            .build();
  }
}
