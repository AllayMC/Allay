package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

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
