package org.allaymc.api.entity.interfaces.hoglin;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityHoglin extends Entity {
  EntityType<EntityHoglin> HOGLIN_TYPE = EntityTypeBuilder
          .builder(EntityHoglin.class)
          .vanillaEntity(VanillaEntityId.HOGLIN)
          .build();
}
