package cn.allay.api.entity.interfaces.witherskull;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityWitherSkull extends Entity {
  EntityType<EntityWitherSkull> WITHER_SKULL_TYPE = EntityTypeBuilder
          .builder(EntityWitherSkull.class)
          .vanillaEntity(VanillaEntityId.WITHER_SKULL)
          .build();
}
