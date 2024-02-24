package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityGlowSquid;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityGlowSquidInitializer {
  static void init() {
    EntityTypes.GLOW_SQUID_TYPE = EntityTypeBuilder
            .builder(EntityGlowSquid.class)
            .vanillaEntity(VanillaEntityId.GLOW_SQUID)
            .build();
  }
}
