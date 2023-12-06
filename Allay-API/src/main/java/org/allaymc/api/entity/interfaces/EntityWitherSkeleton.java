package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityWitherSkeleton extends Entity {
  EntityType<EntityWitherSkeleton> WITHER_SKELETON_TYPE = EntityTypeBuilder
          .builder(EntityWitherSkeleton.class)
          .vanillaEntity(VanillaEntityId.WITHER_SKELETON)
          .build();
}
