package cn.allay.api.entity.interfaces.zombiepigman;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityZombiePigman extends Entity {
  EntityType<EntityZombiePigman> ZOMBIE_PIGMAN_TYPE = EntityTypeBuilder
          .builder(EntityZombiePigman.class)
          .vanillaEntity(VanillaEntityId.ZOMBIE_PIGMAN)
          .build();
}
