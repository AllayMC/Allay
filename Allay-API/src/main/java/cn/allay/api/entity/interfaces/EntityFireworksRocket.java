package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityFireworksRocket extends Entity {
  EntityType<EntityFireworksRocket> FIREWORKS_ROCKET_TYPE = EntityTypeBuilder
          .builder(EntityFireworksRocket.class)
          .vanillaEntity(VanillaEntityId.FIREWORKS_ROCKET)
          .build();
}
