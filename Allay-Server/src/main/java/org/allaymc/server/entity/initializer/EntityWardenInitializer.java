package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityWarden;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityWardenInitializer {
  static void init() {
    EntityTypes.WARDEN_TYPE = EntityTypeBuilder
            .builder(EntityWarden.class)
            .vanillaEntity(VanillaEntityId.WARDEN)
            .build();
  }
}
