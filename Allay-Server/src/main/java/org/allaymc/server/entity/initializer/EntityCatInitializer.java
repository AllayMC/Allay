package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityCat;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityCatInitializer {
  static void init() {
    EntityTypes.CAT_TYPE = EntityTypeBuilder
            .builder(EntityCat.class)
            .vanillaEntity(VanillaEntityId.CAT)
            .build();
  }
}
