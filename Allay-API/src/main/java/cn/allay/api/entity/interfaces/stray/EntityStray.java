package cn.allay.api.entity.interfaces.stray;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityStray extends Entity {
  EntityType<EntityStray> STRAY_TYPE = EntityTypeBuilder
          .builder(EntityStray.class)
          .vanillaEntity(VanillaEntityId.STRAY)
          .build();
}
