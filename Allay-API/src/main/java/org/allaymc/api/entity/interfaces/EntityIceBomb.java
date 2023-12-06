package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityIceBomb extends Entity {
  EntityType<EntityIceBomb> ICE_BOMB_TYPE = EntityTypeBuilder
          .builder(EntityIceBomb.class)
          .vanillaEntity(VanillaEntityId.ICE_BOMB)
          .build();
}
