package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityBalloon;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityBalloonInitializer {
  static void init() {
    EntityTypes.BALLOON_TYPE = EntityTypeBuilder
            .builder(EntityBalloon.class)
            .vanillaEntity(VanillaEntityId.BALLOON)
            .build();
  }
}
