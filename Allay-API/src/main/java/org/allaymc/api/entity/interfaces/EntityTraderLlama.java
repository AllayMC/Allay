package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityTraderLlama extends Entity {
  EntityType<EntityTraderLlama> TRADER_LLAMA_TYPE = EntityTypeBuilder
          .builder(EntityTraderLlama.class)
          .vanillaEntity(VanillaEntityId.TRADER_LLAMA)
          .build();
}
