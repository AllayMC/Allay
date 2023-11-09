package org.allaymc.api.entity.interfaces.fox;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityFox extends Entity {
  EntityType<EntityFox> FOX_TYPE = EntityTypeBuilder
          .builder(EntityFox.class)
          .vanillaEntity(VanillaEntityId.FOX)
          .build();
}
