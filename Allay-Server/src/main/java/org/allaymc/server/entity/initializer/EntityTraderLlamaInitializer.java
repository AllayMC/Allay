package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityTraderLlama;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityTraderLlamaInitializer {
  static void init() {
    EntityTypes.TRADER_LLAMA_TYPE = EntityTypeBuilder
            .builder(EntityTraderLlama.class)
            .vanillaEntity(VanillaEntityId.TRADER_LLAMA)
            .build();
  }
}
