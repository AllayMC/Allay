package org.allaymc.api.entity.interfaces.guardian;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityGuardian extends Entity {
  EntityType<EntityGuardian> GUARDIAN_TYPE = EntityTypeBuilder
          .builder(EntityGuardian.class)
          .vanillaEntity(VanillaEntityId.GUARDIAN)
          .build();
}
