package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityXpBottle;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityXpBottleInitializer {
  static void init() {
    EntityTypes.XP_BOTTLE_TYPE = EntityTypeBuilder
            .builder(EntityXpBottle.class)
            .vanillaEntity(VanillaEntityId.XP_BOTTLE)
            .build();
  }
}
