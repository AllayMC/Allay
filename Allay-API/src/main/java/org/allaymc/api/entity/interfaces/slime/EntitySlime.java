package org.allaymc.api.entity.interfaces.slime;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntitySlime extends Entity {
  EntityType<EntitySlime> SLIME_TYPE = EntityTypeBuilder
          .builder(EntitySlime.class)
          .vanillaEntity(VanillaEntityId.SLIME)
          .build();
}
