package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityZombieHorse extends Entity {
  EntityType<EntityZombieHorse> ZOMBIE_HORSE_TYPE = EntityTypeBuilder
          .builder(EntityZombieHorse.class)
          .vanillaEntity(VanillaEntityId.ZOMBIE_HORSE)
          .build();
}
