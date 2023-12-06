package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityEvocationIllager extends Entity {
  EntityType<EntityEvocationIllager> EVOCATION_ILLAGER_TYPE = EntityTypeBuilder
          .builder(EntityEvocationIllager.class)
          .vanillaEntity(VanillaEntityId.EVOCATION_ILLAGER)
          .build();
}
