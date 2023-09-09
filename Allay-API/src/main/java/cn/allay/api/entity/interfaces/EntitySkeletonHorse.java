package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

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
