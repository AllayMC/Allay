package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityPillager;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityPillagerInitializer {
  static void init() {
    EntityTypes.PILLAGER_TYPE = EntityTypeBuilder
            .builder(EntityPillager.class)
            .vanillaEntity(VanillaEntityId.PILLAGER)
            .build();
  }
}
