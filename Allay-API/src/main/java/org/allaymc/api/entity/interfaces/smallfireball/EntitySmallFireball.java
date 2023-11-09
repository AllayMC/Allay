package org.allaymc.api.entity.interfaces.smallfireball;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntitySmallFireball extends Entity {
  EntityType<EntitySmallFireball> SMALL_FIREBALL_TYPE = EntityTypeBuilder
          .builder(EntitySmallFireball.class)
          .vanillaEntity(VanillaEntityId.SMALL_FIREBALL)
          .build();
}
