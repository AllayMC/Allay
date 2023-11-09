package org.allaymc.api.entity.interfaces.shulker;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityShulker extends Entity {
  EntityType<EntityShulker> SHULKER_TYPE = EntityTypeBuilder
          .builder(EntityShulker.class)
          .vanillaEntity(VanillaEntityId.SHULKER)
          .build();
}
