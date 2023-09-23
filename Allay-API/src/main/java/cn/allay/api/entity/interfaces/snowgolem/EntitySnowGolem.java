package cn.allay.api.entity.interfaces.snowgolem;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntitySnowGolem extends Entity {
  EntityType<EntitySnowGolem> SNOW_GOLEM_TYPE = EntityTypeBuilder
          .builder(EntitySnowGolem.class)
          .vanillaEntity(VanillaEntityId.SNOW_GOLEM)
          .build();
}
