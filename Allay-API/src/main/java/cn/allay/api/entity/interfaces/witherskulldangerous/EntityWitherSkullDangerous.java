package cn.allay.api.entity.interfaces.witherskulldangerous;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityWitherSkullDangerous extends Entity {
  EntityType<EntityWitherSkullDangerous> WITHER_SKULL_DANGEROUS_TYPE = EntityTypeBuilder
          .builder(EntityWitherSkullDangerous.class)
          .vanillaEntity(VanillaEntityId.WITHER_SKULL_DANGEROUS)
          .build();
}
