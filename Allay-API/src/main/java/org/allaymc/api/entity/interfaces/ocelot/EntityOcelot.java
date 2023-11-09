package org.allaymc.api.entity.interfaces.ocelot;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityOcelot extends Entity {
  EntityType<EntityOcelot> OCELOT_TYPE = EntityTypeBuilder
          .builder(EntityOcelot.class)
          .vanillaEntity(VanillaEntityId.OCELOT)
          .build();
}
