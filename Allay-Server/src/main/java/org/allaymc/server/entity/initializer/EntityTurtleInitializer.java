package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityTurtle;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityTurtleInitializer {
  static void init() {
    EntityTypes.TURTLE_TYPE = EntityTypeBuilder
            .builder(EntityTurtle.class)
            .vanillaEntity(VanillaEntityId.TURTLE)
            .build();
  }
}
