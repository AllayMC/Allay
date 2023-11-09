package org.allaymc.api.entity.interfaces.xpbottle;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityXpBottle extends Entity {
  EntityType<EntityXpBottle> XP_BOTTLE_TYPE = EntityTypeBuilder
          .builder(EntityXpBottle.class)
          .vanillaEntity(VanillaEntityId.XP_BOTTLE)
          .build();
}
