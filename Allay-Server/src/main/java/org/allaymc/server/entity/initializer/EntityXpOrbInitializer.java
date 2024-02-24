package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityXpOrb;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityXpOrbInitializer {
  static void init() {
    EntityTypes.XP_ORB_TYPE = EntityTypeBuilder
            .builder(EntityXpOrb.class)
            .vanillaEntity(VanillaEntityId.XP_ORB)
            .build();
  }
}
