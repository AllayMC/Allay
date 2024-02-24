package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityLeashKnot;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityLeashKnotInitializer {
  static void init() {
    EntityTypes.LEASH_KNOT_TYPE = EntityTypeBuilder
            .builder(EntityLeashKnot.class)
            .vanillaEntity(VanillaEntityId.LEASH_KNOT)
            .build();
  }
}
