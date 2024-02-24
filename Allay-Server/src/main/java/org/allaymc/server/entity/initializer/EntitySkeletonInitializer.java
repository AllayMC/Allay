package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntitySkeleton;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntitySkeletonInitializer {
  static void init() {
    EntityTypes.SKELETON_TYPE = EntityTypeBuilder
            .builder(EntitySkeleton.class)
            .vanillaEntity(VanillaEntityId.SKELETON)
            .build();
  }
}
