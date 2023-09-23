package cn.allay.api.entity.interfaces.blaze;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityBlaze extends Entity {
  EntityType<EntityBlaze> BLAZE_TYPE = EntityTypeBuilder
          .builder(EntityBlaze.class)
          .vanillaEntity(VanillaEntityId.BLAZE)
          .build();
}
