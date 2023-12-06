package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityGhast extends Entity {
  EntityType<EntityGhast> GHAST_TYPE = EntityTypeBuilder
          .builder(EntityGhast.class)
          .vanillaEntity(VanillaEntityId.GHAST)
          .build();
}
