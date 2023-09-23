package cn.allay.api.entity.interfaces.vex;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityVex extends Entity {
  EntityType<EntityVex> VEX_TYPE = EntityTypeBuilder
          .builder(EntityVex.class)
          .vanillaEntity(VanillaEntityId.VEX)
          .build();
}
