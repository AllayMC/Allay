package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityWitherSkeleton;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityWitherSkeletonInitializer {
  static void init() {
    EntityTypes.WITHER_SKELETON_TYPE = EntityTypeBuilder
            .builder(EntityWitherSkeleton.class)
            .vanillaEntity(VanillaEntityId.WITHER_SKELETON)
            .build();
  }
}
