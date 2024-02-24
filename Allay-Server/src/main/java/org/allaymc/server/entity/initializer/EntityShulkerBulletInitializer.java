package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityShulkerBullet;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityShulkerBulletInitializer {
  static void init() {
    EntityTypes.SHULKER_BULLET_TYPE = EntityTypeBuilder
            .builder(EntityShulkerBullet.class)
            .vanillaEntity(VanillaEntityId.SHULKER_BULLET)
            .build();
  }
}
