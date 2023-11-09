package org.allaymc.api.entity.interfaces.creeper;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityCreeper extends Entity {
  EntityType<EntityCreeper> CREEPER_TYPE = EntityTypeBuilder
          .builder(EntityCreeper.class)
          .vanillaEntity(VanillaEntityId.CREEPER)
          .build();
}
