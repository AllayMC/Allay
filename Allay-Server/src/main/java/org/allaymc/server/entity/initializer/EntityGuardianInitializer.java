package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityGuardian;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityGuardianInitializer {
  static void init() {
    EntityTypes.GUARDIAN_TYPE = EntityTypeBuilder
            .builder(EntityGuardian.class)
            .vanillaEntity(VanillaEntityId.GUARDIAN)
            .build();
  }
}
