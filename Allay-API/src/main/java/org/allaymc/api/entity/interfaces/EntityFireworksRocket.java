package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityFireworksRocket extends Entity {
  EntityType<EntityFireworksRocket> FIREWORKS_ROCKET_TYPE = EntityTypeBuilder
          .builder(EntityFireworksRocket.class)
          .vanillaEntity(VanillaEntityId.FIREWORKS_ROCKET)
          .build();
}
