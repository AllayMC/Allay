package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

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
