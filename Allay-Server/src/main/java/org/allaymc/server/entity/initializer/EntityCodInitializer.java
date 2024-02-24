package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityCod;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityCodInitializer {
  static void init() {
    EntityTypes.COD_TYPE = EntityTypeBuilder
            .builder(EntityCod.class)
            .vanillaEntity(VanillaEntityId.COD)
            .build();
  }
}
