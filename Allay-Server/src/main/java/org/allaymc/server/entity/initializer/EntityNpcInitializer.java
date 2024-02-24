package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityNpc;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityNpcInitializer {
  static void init() {
    EntityTypes.NPC_TYPE = EntityTypeBuilder
            .builder(EntityNpc.class)
            .vanillaEntity(VanillaEntityId.NPC)
            .build();
  }
}
