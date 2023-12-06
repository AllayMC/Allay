package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityWitherSkullDangerous extends Entity {
  EntityType<EntityWitherSkullDangerous> WITHER_SKULL_DANGEROUS_TYPE = EntityTypeBuilder
          .builder(EntityWitherSkullDangerous.class)
          .vanillaEntity(VanillaEntityId.WITHER_SKULL_DANGEROUS)
          .build();
}
