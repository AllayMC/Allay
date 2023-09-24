package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityDragonFireball extends Entity {
  EntityType<EntityDragonFireball> DRAGON_FIREBALL_TYPE = EntityTypeBuilder
          .builder(EntityDragonFireball.class)
          .vanillaEntity(VanillaEntityId.DRAGON_FIREBALL)
          .build();
}
