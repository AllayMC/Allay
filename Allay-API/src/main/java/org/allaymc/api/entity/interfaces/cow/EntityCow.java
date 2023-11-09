package org.allaymc.api.entity.interfaces.cow;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityCow extends Entity {
  EntityType<EntityCow> COW_TYPE = EntityTypeBuilder
          .builder(EntityCow.class)
          .vanillaEntity(VanillaEntityId.COW)
          .build();
}
