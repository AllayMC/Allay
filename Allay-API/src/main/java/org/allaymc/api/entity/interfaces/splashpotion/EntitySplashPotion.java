package org.allaymc.api.entity.interfaces.splashpotion;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntitySplashPotion extends Entity {
  EntityType<EntitySplashPotion> SPLASH_POTION_TYPE = EntityTypeBuilder
          .builder(EntitySplashPotion.class)
          .vanillaEntity(VanillaEntityId.SPLASH_POTION)
          .build();
}
