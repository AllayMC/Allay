package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityCamel;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityCamelInitializer {
  static void init() {
    EntityTypes.CAMEL_TYPE = EntityTypeBuilder
            .builder(EntityCamel.class)
            .vanillaEntity(VanillaEntityId.CAMEL)
            .build();
  }
}
