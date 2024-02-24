package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityChicken;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityChickenInitializer {
  static void init() {
    EntityTypes.CHICKEN_TYPE = EntityTypeBuilder
            .builder(EntityChicken.class)
            .vanillaEntity(VanillaEntityId.CHICKEN)
            .build();
  }
}
