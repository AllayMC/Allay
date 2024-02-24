package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityVindicator;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityVindicatorInitializer {
  static void init() {
    EntityTypes.VINDICATOR_TYPE = EntityTypeBuilder
            .builder(EntityVindicator.class)
            .vanillaEntity(VanillaEntityId.VINDICATOR)
            .build();
  }
}
