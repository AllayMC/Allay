package org.allaymc.api.entity.interfaces.chicken;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityChicken extends Entity {
  EntityType<EntityChicken> CHICKEN_TYPE = EntityTypeBuilder
          .builder(EntityChicken.class)
          .vanillaEntity(VanillaEntityId.CHICKEN)
          .build();
}
