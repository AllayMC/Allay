package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityVillager;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityVillagerInitializer {
  static void init() {
    EntityTypes.VILLAGER_TYPE = EntityTypeBuilder
            .builder(EntityVillager.class)
            .vanillaEntity(VanillaEntityId.VILLAGER)
            .build();
  }
}
