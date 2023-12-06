package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityChestBoat extends Entity {
  EntityType<EntityChestBoat> CHEST_BOAT_TYPE = EntityTypeBuilder
          .builder(EntityChestBoat.class)
          .vanillaEntity(VanillaEntityId.CHEST_BOAT)
          .build();
}
