package org.allaymc.api.entity.interfaces.tnt;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityTnt extends Entity {
  EntityType<EntityTnt> TNT_TYPE = EntityTypeBuilder
          .builder(EntityTnt.class)
          .vanillaEntity(VanillaEntityId.TNT)
          .build();
}
