package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityMinecart;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityMinecartInitializer {
  static void init() {
    EntityTypes.MINECART_TYPE = EntityTypeBuilder
            .builder(EntityMinecart.class)
            .vanillaEntity(VanillaEntityId.MINECART)
            .build();
  }
}
