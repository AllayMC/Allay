package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntitySnowGolem extends Entity {
  EntityType<EntitySnowGolem> SNOW_GOLEM_TYPE = EntityTypeBuilder
          .builder(EntitySnowGolem.class)
          .vanillaEntity(VanillaEntityId.SNOW_GOLEM)
          .build();
}
