package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityWolf extends Entity {
  EntityType<EntityWolf> WOLF_TYPE = EntityTypeBuilder
          .builder(EntityWolf.class)
          .vanillaEntity(VanillaEntityId.WOLF)
          .build();
}
