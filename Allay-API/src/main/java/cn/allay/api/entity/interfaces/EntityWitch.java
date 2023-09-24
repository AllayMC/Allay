package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityWitch extends Entity {
  EntityType<EntityWitch> WITCH_TYPE = EntityTypeBuilder
          .builder(EntityWitch.class)
          .vanillaEntity(VanillaEntityId.WITCH)
          .build();
}
