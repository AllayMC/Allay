package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityLingeringPotion;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityLingeringPotionInitializer {
  static void init() {
    EntityTypes.LINGERING_POTION_TYPE = EntityTypeBuilder
            .builder(EntityLingeringPotion.class)
            .vanillaEntity(VanillaEntityId.LINGERING_POTION)
            .build();
  }
}
