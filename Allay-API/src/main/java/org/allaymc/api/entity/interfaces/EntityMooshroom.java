package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityMooshroom extends Entity {
  EntityType<EntityMooshroom> MOOSHROOM_TYPE = EntityTypeBuilder
          .builder(EntityMooshroom.class)
          .vanillaEntity(VanillaEntityId.MOOSHROOM)
          .build();
}
