package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityPanda extends Entity {
  EntityType<EntityPanda> PANDA_TYPE = EntityTypeBuilder
          .builder(EntityPanda.class)
          .vanillaEntity(VanillaEntityId.PANDA)
          .build();
}
