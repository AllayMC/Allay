package org.allaymc.api.entity.interfaces.zombievillagerv2;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

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
