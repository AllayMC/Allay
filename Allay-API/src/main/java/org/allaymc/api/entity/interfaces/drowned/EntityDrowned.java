package org.allaymc.api.entity.interfaces.drowned;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityDrowned extends Entity {
  EntityType<EntityDrowned> DROWNED_TYPE = EntityTypeBuilder
          .builder(EntityDrowned.class)
          .vanillaEntity(VanillaEntityId.DROWNED)
          .build();
}
