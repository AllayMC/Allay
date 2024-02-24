package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityParrot;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityParrotInitializer {
  static void init() {
    EntityTypes.PARROT_TYPE = EntityTypeBuilder
            .builder(EntityParrot.class)
            .vanillaEntity(VanillaEntityId.PARROT)
            .build();
  }
}
