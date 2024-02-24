package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntitySilverfish;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntitySilverfishInitializer {
  static void init() {
    EntityTypes.SILVERFISH_TYPE = EntityTypeBuilder
            .builder(EntitySilverfish.class)
            .vanillaEntity(VanillaEntityId.SILVERFISH)
            .build();
  }
}
