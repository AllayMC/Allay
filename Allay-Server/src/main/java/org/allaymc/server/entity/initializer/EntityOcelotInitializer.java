package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityOcelot;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityOcelotInitializer {
  static void init() {
    EntityTypes.OCELOT_TYPE = EntityTypeBuilder
            .builder(EntityOcelot.class)
            .vanillaEntity(VanillaEntityId.OCELOT)
            .build();
  }
}
