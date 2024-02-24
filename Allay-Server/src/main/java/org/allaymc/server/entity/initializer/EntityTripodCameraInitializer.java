package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityTripodCamera;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityTripodCameraInitializer {
  static void init() {
    EntityTypes.TRIPOD_CAMERA_TYPE = EntityTypeBuilder
            .builder(EntityTripodCamera.class)
            .vanillaEntity(VanillaEntityId.TRIPOD_CAMERA)
            .build();
  }
}
