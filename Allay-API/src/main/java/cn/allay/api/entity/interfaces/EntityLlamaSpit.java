package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityLlamaSpit extends Entity {
  EntityType<EntityLlamaSpit> LLAMA_SPIT_TYPE = EntityTypeBuilder
          .builder(EntityLlamaSpit.class)
          .vanillaEntity(VanillaEntityId.LLAMA_SPIT)
          .build();
}
