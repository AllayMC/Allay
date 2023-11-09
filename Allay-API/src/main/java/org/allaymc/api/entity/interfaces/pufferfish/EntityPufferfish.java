package org.allaymc.api.entity.interfaces.pufferfish;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityPufferfish extends Entity {
  EntityType<EntityPufferfish> PUFFERFISH_TYPE = EntityTypeBuilder
          .builder(EntityPufferfish.class)
          .vanillaEntity(VanillaEntityId.PUFFERFISH)
          .build();
}
