package org.allaymc.api.entity.interfaces.parrot;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityParrot extends Entity {
  EntityType<EntityParrot> PARROT_TYPE = EntityTypeBuilder
          .builder(EntityParrot.class)
          .vanillaEntity(VanillaEntityId.PARROT)
          .build();
}
