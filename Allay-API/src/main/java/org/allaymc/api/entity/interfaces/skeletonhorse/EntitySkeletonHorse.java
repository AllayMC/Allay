package org.allaymc.api.entity.interfaces.skeletonhorse;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntitySkeletonHorse extends Entity {
  EntityType<EntitySkeletonHorse> SKELETON_HORSE_TYPE = EntityTypeBuilder
          .builder(EntitySkeletonHorse.class)
          .vanillaEntity(VanillaEntityId.SKELETON_HORSE)
          .build();
}
