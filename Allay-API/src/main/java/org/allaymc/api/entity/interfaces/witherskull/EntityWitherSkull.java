package org.allaymc.api.entity.interfaces.witherskull;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityWitherSkull extends Entity {
  EntityType<EntityWitherSkull> WITHER_SKULL_TYPE = EntityTypeBuilder
          .builder(EntityWitherSkull.class)
          .vanillaEntity(VanillaEntityId.WITHER_SKULL)
          .build();
}
