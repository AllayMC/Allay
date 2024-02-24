package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityIronGolem;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityIronGolemInitializer {
  static void init() {
    EntityTypes.IRON_GOLEM_TYPE = EntityTypeBuilder
            .builder(EntityIronGolem.class)
            .vanillaEntity(VanillaEntityId.IRON_GOLEM)
            .build();
  }
}
