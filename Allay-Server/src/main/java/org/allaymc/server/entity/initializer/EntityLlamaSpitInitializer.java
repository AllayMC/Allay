package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityLlamaSpit;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityLlamaSpitInitializer {
  static void init() {
    EntityTypes.LLAMA_SPIT_TYPE = EntityTypeBuilder
            .builder(EntityLlamaSpit.class)
            .vanillaEntity(VanillaEntityId.LLAMA_SPIT)
            .build();
  }
}
