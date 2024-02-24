package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityLlama;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityLlamaInitializer {
  static void init() {
    EntityTypes.LLAMA_TYPE = EntityTypeBuilder
            .builder(EntityLlama.class)
            .vanillaEntity(VanillaEntityId.LLAMA)
            .build();
  }
}
