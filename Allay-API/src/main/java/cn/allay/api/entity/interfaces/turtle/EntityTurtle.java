package cn.allay.api.entity.interfaces.turtle;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityTurtle extends Entity {
  EntityType<EntityTurtle> TURTLE_TYPE = EntityTypeBuilder
          .builder(EntityTurtle.class)
          .vanillaEntity(VanillaEntityId.TURTLE)
          .build();
}
