package cn.allay.api.entity.interfaces.wither;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityWither extends Entity {
  EntityType<EntityWither> WITHER_TYPE = EntityTypeBuilder
          .builder(EntityWither.class)
          .vanillaEntity(VanillaEntityId.WITHER)
          .build();
}
