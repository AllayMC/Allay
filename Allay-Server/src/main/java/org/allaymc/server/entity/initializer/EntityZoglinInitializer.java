package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityZoglin;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityZoglinInitializer {
  static void init() {
    EntityTypes.ZOGLIN_TYPE = EntityTypeBuilder
            .builder(EntityZoglin.class)
            .vanillaEntity(VanillaEntityId.ZOGLIN)
            .build();
  }
}
