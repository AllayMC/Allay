package org.allaymc.api.entity.interfaces.tripodcamera;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

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
