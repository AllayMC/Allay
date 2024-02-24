package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityChestMinecart;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityChestMinecartInitializer {
  static void init() {
    EntityTypes.CHEST_MINECART_TYPE = EntityTypeBuilder
            .builder(EntityChestMinecart.class)
            .vanillaEntity(VanillaEntityId.CHEST_MINECART)
            .build();
  }
}
