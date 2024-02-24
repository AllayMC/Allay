package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityBee;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityBeeInitializer {
  static void init() {
    EntityTypes.BEE_TYPE = EntityTypeBuilder
            .builder(EntityBee.class)
            .vanillaEntity(VanillaEntityId.BEE)
            .build();
  }
}
