package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityAgent;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityAgentInitializer {
  static void init() {
    EntityTypes.AGENT_TYPE = EntityTypeBuilder
            .builder(EntityAgent.class)
            .vanillaEntity(VanillaEntityId.AGENT)
            .build();
  }
}
