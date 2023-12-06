package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityAgent extends Entity {
  EntityType<EntityAgent> AGENT_TYPE = EntityTypeBuilder
          .builder(EntityAgent.class)
          .vanillaEntity(VanillaEntityId.AGENT)
          .build();
}
