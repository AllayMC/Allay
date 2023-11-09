package org.allaymc.api.entity.interfaces.polarbear;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityPolarBear extends Entity {
  EntityType<EntityPolarBear> POLAR_BEAR_TYPE = EntityTypeBuilder
          .builder(EntityPolarBear.class)
          .vanillaEntity(VanillaEntityId.POLAR_BEAR)
          .build();
}
