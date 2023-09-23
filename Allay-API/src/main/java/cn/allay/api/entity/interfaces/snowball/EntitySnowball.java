package cn.allay.api.entity.interfaces.snowball;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntitySnowball extends Entity {
  EntityType<EntitySnowball> SNOWBALL_TYPE = EntityTypeBuilder
          .builder(EntitySnowball.class)
          .vanillaEntity(VanillaEntityId.SNOWBALL)
          .build();
}
