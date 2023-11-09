package org.allaymc.api.entity.interfaces.witch;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityWitch extends Entity {
  EntityType<EntityWitch> WITCH_TYPE = EntityTypeBuilder
          .builder(EntityWitch.class)
          .vanillaEntity(VanillaEntityId.WITCH)
          .build();
}
