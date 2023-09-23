package cn.allay.api.entity.interfaces.witherskeleton;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

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
