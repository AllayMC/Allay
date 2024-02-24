package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntitySniffer;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntitySnifferInitializer {
  static void init() {
    EntityTypes.SNIFFER_TYPE = EntityTypeBuilder
            .builder(EntitySniffer.class)
            .vanillaEntity(VanillaEntityId.SNIFFER)
            .build();
  }
}
