package cn.allay.api.entity.interfaces.pig;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityPig extends Entity {
  EntityType<EntityPig> PIG_TYPE = EntityTypeBuilder
          .builder(EntityPig.class)
          .vanillaEntity(VanillaEntityId.PIG)
          .build();
}
