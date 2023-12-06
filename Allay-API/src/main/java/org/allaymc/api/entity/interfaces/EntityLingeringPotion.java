package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityLingeringPotion extends Entity {
  EntityType<EntityLingeringPotion> LINGERING_POTION_TYPE = EntityTypeBuilder
          .builder(EntityLingeringPotion.class)
          .vanillaEntity(VanillaEntityId.LINGERING_POTION)
          .build();
}
