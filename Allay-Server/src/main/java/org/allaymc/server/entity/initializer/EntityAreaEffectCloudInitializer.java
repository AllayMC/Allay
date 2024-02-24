package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityAreaEffectCloud;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityAreaEffectCloudInitializer {
  static void init() {
    EntityTypes.AREA_EFFECT_CLOUD_TYPE = EntityTypeBuilder
            .builder(EntityAreaEffectCloud.class)
            .vanillaEntity(VanillaEntityId.AREA_EFFECT_CLOUD)
            .build();
  }
}
