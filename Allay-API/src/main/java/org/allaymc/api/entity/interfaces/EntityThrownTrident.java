package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityThrownTrident extends Entity {
  EntityType<EntityThrownTrident> THROWN_TRIDENT_TYPE = EntityTypeBuilder
          .builder(EntityThrownTrident.class)
          .vanillaEntity(VanillaEntityId.THROWN_TRIDENT)
          .build();
}
