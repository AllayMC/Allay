package org.allaymc.api.entity.interfaces.boat;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityBoat extends Entity {
  EntityType<EntityBoat> BOAT_TYPE = EntityTypeBuilder
          .builder(EntityBoat.class)
          .vanillaEntity(VanillaEntityId.BOAT)
          .build();
}
