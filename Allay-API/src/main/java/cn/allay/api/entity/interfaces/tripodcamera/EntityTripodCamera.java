package cn.allay.api.entity.interfaces.tripodcamera;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityTripodCamera extends Entity {
  EntityType<EntityTripodCamera> TRIPOD_CAMERA_TYPE = EntityTypeBuilder
          .builder(EntityTripodCamera.class)
          .vanillaEntity(VanillaEntityId.TRIPOD_CAMERA)
          .build();
}
