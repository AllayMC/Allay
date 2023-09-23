package cn.allay.api.entity.interfaces.husk;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityHusk extends Entity {
  EntityType<EntityHusk> HUSK_TYPE = EntityTypeBuilder
          .builder(EntityHusk.class)
          .vanillaEntity(VanillaEntityId.HUSK)
          .build();
}
