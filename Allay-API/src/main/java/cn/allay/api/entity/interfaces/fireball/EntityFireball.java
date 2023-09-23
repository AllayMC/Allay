package cn.allay.api.entity.interfaces.fireball;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityFireball extends Entity {
  EntityType<EntityFireball> FIREBALL_TYPE = EntityTypeBuilder
          .builder(EntityFireball.class)
          .vanillaEntity(VanillaEntityId.FIREBALL)
          .build();
}
