package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntitySquid;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntitySquidInitializer {
  static void init() {
    EntityTypes.SQUID_TYPE = EntityTypeBuilder
            .builder(EntitySquid.class)
            .vanillaEntity(VanillaEntityId.SQUID)
            .build();
  }
}
