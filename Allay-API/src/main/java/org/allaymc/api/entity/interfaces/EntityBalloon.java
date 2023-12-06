package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityBalloon extends Entity {
  EntityType<EntityBalloon> BALLOON_TYPE = EntityTypeBuilder
          .builder(EntityBalloon.class)
          .vanillaEntity(VanillaEntityId.BALLOON)
          .build();
}
