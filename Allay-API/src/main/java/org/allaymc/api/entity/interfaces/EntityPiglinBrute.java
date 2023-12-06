package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityPiglinBrute extends Entity {
  EntityType<EntityPiglinBrute> PIGLIN_BRUTE_TYPE = EntityTypeBuilder
          .builder(EntityPiglinBrute.class)
          .vanillaEntity(VanillaEntityId.PIGLIN_BRUTE)
          .build();
}
