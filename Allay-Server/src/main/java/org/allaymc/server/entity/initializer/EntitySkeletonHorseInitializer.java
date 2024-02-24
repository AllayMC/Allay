package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntitySkeletonHorse;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntitySkeletonHorseInitializer {
  static void init() {
    EntityTypes.SKELETON_HORSE_TYPE = EntityTypeBuilder
            .builder(EntitySkeletonHorse.class)
            .vanillaEntity(VanillaEntityId.SKELETON_HORSE)
            .build();
  }
}
