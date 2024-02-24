package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityArrow;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityArrowInitializer {
  static void init() {
    EntityTypes.ARROW_TYPE = EntityTypeBuilder
            .builder(EntityArrow.class)
            .vanillaEntity(VanillaEntityId.ARROW)
            .build();
  }
}
