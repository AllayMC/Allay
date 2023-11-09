package org.allaymc.api.entity.interfaces.horse;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityHorse extends Entity {
  EntityType<EntityHorse> HORSE_TYPE = EntityTypeBuilder
          .builder(EntityHorse.class)
          .vanillaEntity(VanillaEntityId.HORSE)
          .build();
}
