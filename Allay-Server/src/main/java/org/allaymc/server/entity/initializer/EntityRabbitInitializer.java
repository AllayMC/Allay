package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityRabbit;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityRabbitInitializer {
  static void init() {
    EntityTypes.RABBIT_TYPE = EntityTypeBuilder
            .builder(EntityRabbit.class)
            .vanillaEntity(VanillaEntityId.RABBIT)
            .build();
  }
}
