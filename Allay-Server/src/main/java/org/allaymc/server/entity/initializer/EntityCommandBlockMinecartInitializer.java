package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityCommandBlockMinecart;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityCommandBlockMinecartInitializer {
  static void init() {
    EntityTypes.COMMAND_BLOCK_MINECART_TYPE = EntityTypeBuilder
            .builder(EntityCommandBlockMinecart.class)
            .vanillaEntity(VanillaEntityId.COMMAND_BLOCK_MINECART)
            .build();
  }
}
