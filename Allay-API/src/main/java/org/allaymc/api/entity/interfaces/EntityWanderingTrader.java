package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityWanderingTrader extends Entity {
  EntityType<EntityWanderingTrader> WANDERING_TRADER_TYPE = EntityTypeBuilder
          .builder(EntityWanderingTrader.class)
          .vanillaEntity(VanillaEntityId.WANDERING_TRADER)
          .build();
}
