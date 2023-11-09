package org.allaymc.api.entity.interfaces.snowball;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntitySnowball extends Entity {
  EntityType<EntitySnowball> SNOWBALL_TYPE = EntityTypeBuilder
          .builder(EntitySnowball.class)
          .vanillaEntity(VanillaEntityId.SNOWBALL)
          .build();
}
