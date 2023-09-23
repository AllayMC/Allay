package cn.allay.api.entity.interfaces.cow;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityCow extends Entity {
  EntityType<EntityCow> COW_TYPE = EntityTypeBuilder
          .builder(EntityCow.class)
          .vanillaEntity(VanillaEntityId.COW)
          .build();
}
