package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityTropicalfish extends Entity {
  EntityType<EntityTropicalfish> TROPICALFISH_TYPE = EntityTypeBuilder
          .builder(EntityTropicalfish.class)
          .vanillaEntity(VanillaEntityId.TROPICALFISH)
          .build();
}
