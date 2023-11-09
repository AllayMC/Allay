package org.allaymc.api.entity.interfaces.zombie;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityZombie extends Entity {
  EntityType<EntityZombie> ZOMBIE_TYPE = EntityTypeBuilder
          .builder(EntityZombie.class)
          .vanillaEntity(VanillaEntityId.ZOMBIE)
          .build();
}
