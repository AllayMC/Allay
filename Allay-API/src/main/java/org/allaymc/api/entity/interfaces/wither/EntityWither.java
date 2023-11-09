package org.allaymc.api.entity.interfaces.wither;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityWither extends Entity {
  EntityType<EntityWither> WITHER_TYPE = EntityTypeBuilder
          .builder(EntityWither.class)
          .vanillaEntity(VanillaEntityId.WITHER)
          .build();
}
