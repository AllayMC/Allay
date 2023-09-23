package cn.allay.api.entity.interfaces.traderllama;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

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
