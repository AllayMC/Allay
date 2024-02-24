package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityElderGuardian;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityElderGuardianInitializer {
  static void init() {
    EntityTypes.ELDER_GUARDIAN_TYPE = EntityTypeBuilder
            .builder(EntityElderGuardian.class)
            .vanillaEntity(VanillaEntityId.ELDER_GUARDIAN)
            .build();
  }
}
