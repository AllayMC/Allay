package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityWanderingTrader;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityWanderingTraderInitializer {
  static void init() {
    EntityTypes.WANDERING_TRADER_TYPE = EntityTypeBuilder
            .builder(EntityWanderingTrader.class)
            .vanillaEntity(VanillaEntityId.WANDERING_TRADER)
            .build();
  }
}
