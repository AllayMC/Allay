package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityStrider;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityStriderInitializer {
  static void init() {
    EntityTypes.STRIDER_TYPE = EntityTypeBuilder
            .builder(EntityStrider.class)
            .vanillaEntity(VanillaEntityId.STRIDER)
            .build();
  }
}
