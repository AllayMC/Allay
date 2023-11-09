package org.allaymc.api.entity.interfaces.salmon;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntitySalmon extends Entity {
  EntityType<EntitySalmon> SALMON_TYPE = EntityTypeBuilder
          .builder(EntitySalmon.class)
          .vanillaEntity(VanillaEntityId.SALMON)
          .build();
}
