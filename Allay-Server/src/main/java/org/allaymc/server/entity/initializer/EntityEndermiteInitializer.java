package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityEndermite;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityEndermiteInitializer {
  static void init() {
    EntityTypes.ENDERMITE_TYPE = EntityTypeBuilder
            .builder(EntityEndermite.class)
            .vanillaEntity(VanillaEntityId.ENDERMITE)
            .build();
  }
}
