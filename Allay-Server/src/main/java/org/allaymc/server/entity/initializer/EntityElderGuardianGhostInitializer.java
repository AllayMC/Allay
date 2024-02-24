package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityElderGuardianGhost;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityElderGuardianGhostInitializer {
  static void init() {
    EntityTypes.ELDER_GUARDIAN_GHOST_TYPE = EntityTypeBuilder
            .builder(EntityElderGuardianGhost.class)
            .vanillaEntity(VanillaEntityId.ELDER_GUARDIAN_GHOST)
            .build();
  }
}
