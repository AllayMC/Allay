package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityBee extends Entity {
  EntityType<EntityBee> BEE_TYPE = EntityTypeBuilder
          .builder(EntityBee.class)
          .vanillaEntity(VanillaEntityId.BEE)
          .build();
}
