package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityPhantom extends Entity {
  EntityType<EntityPhantom> PHANTOM_TYPE = EntityTypeBuilder
          .builder(EntityPhantom.class)
          .vanillaEntity(VanillaEntityId.PHANTOM)
          .build();
}
