package cn.allay.api.entity.interfaces.balloon;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityBalloon extends Entity {
  EntityType<EntityBalloon> BALLOON_TYPE = EntityTypeBuilder
          .builder(EntityBalloon.class)
          .vanillaEntity(VanillaEntityId.BALLOON)
          .build();
}
