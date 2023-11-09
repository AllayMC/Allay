package org.allaymc.api.entity.interfaces.dolphin;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityDolphin extends Entity {
  EntityType<EntityDolphin> DOLPHIN_TYPE = EntityTypeBuilder
          .builder(EntityDolphin.class)
          .vanillaEntity(VanillaEntityId.DOLPHIN)
          .build();
}
