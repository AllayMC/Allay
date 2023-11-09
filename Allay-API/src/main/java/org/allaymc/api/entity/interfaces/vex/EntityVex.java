package org.allaymc.api.entity.interfaces.vex;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityVex extends Entity {
  EntityType<EntityVex> VEX_TYPE = EntityTypeBuilder
          .builder(EntityVex.class)
          .vanillaEntity(VanillaEntityId.VEX)
          .build();
}
