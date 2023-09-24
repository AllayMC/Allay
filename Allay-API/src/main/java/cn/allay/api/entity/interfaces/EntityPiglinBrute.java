package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityPiglinBrute extends Entity {
  EntityType<EntityPiglinBrute> PIGLIN_BRUTE_TYPE = EntityTypeBuilder
          .builder(EntityPiglinBrute.class)
          .vanillaEntity(VanillaEntityId.PIGLIN_BRUTE)
          .build();
}
