package org.allaymc.api.entity.interfaces.rabbit;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityRabbit extends Entity {
  EntityType<EntityRabbit> RABBIT_TYPE = EntityTypeBuilder
          .builder(EntityRabbit.class)
          .vanillaEntity(VanillaEntityId.RABBIT)
          .build();
}
