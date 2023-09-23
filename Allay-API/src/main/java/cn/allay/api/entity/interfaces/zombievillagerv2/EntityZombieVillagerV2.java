package cn.allay.api.entity.interfaces.zombievillagerv2;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityZombieVillagerV2 extends Entity {
  EntityType<EntityZombieVillagerV2> ZOMBIE_VILLAGER_V2_TYPE = EntityTypeBuilder
          .builder(EntityZombieVillagerV2.class)
          .vanillaEntity(VanillaEntityId.ZOMBIE_VILLAGER_V2)
          .build();
}
