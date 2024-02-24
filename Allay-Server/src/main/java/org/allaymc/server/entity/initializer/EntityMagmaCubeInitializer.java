package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityMagmaCube;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityMagmaCubeInitializer {
  static void init() {
    EntityTypes.MAGMA_CUBE_TYPE = EntityTypeBuilder
            .builder(EntityMagmaCube.class)
            .vanillaEntity(VanillaEntityId.MAGMA_CUBE)
            .build();
  }
}
