package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityIronGolem extends Entity {
  EntityType<EntityIronGolem> IRON_GOLEM_TYPE = EntityTypeBuilder
          .builder(EntityIronGolem.class)
          .vanillaEntity(VanillaEntityId.IRON_GOLEM)
          .build();
}
