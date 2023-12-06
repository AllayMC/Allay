package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityShulkerBullet extends Entity {
  EntityType<EntityShulkerBullet> SHULKER_BULLET_TYPE = EntityTypeBuilder
          .builder(EntityShulkerBullet.class)
          .vanillaEntity(VanillaEntityId.SHULKER_BULLET)
          .build();
}
