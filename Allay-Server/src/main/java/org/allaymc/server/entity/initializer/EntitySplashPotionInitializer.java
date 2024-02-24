package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntitySplashPotion;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntitySplashPotionInitializer {
  static void init() {
    EntityTypes.SPLASH_POTION_TYPE = EntityTypeBuilder
            .builder(EntitySplashPotion.class)
            .vanillaEntity(VanillaEntityId.SPLASH_POTION)
            .build();
  }
}
