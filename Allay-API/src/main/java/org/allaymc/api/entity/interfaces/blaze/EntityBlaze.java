package org.allaymc.api.entity.interfaces.blaze;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityBlaze extends Entity {
  EntityType<EntityBlaze> BLAZE_TYPE = EntityTypeBuilder
          .builder(EntityBlaze.class)
          .vanillaEntity(VanillaEntityId.BLAZE)
          .build();
}
