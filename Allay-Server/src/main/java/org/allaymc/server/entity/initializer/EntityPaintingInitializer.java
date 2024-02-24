package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityPainting;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityPaintingInitializer {
  static void init() {
    EntityTypes.PAINTING_TYPE = EntityTypeBuilder
            .builder(EntityPainting.class)
            .vanillaEntity(VanillaEntityId.PAINTING)
            .build();
  }
}
