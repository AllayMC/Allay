package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityPhantom;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityPhantomInitializer {
  static void init() {
    EntityTypes.PHANTOM_TYPE = EntityTypeBuilder
            .builder(EntityPhantom.class)
            .vanillaEntity(VanillaEntityId.PHANTOM)
            .build();
  }
}
