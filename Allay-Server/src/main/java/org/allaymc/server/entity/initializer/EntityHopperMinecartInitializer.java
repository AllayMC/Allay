package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityHopperMinecart;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityHopperMinecartInitializer {
  static void init() {
    EntityTypes.HOPPER_MINECART_TYPE = EntityTypeBuilder
            .builder(EntityHopperMinecart.class)
            .vanillaEntity(VanillaEntityId.HOPPER_MINECART)
            .build();
  }
}
