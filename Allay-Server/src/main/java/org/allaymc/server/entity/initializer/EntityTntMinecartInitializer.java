package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityTntMinecart;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityTntMinecartInitializer {
  static void init() {
    EntityTypes.TNT_MINECART_TYPE = EntityTypeBuilder
            .builder(EntityTntMinecart.class)
            .vanillaEntity(VanillaEntityId.TNT_MINECART)
            .build();
  }
}
