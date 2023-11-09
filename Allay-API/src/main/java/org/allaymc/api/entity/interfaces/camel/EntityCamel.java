package org.allaymc.api.entity.interfaces.camel;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityCamel extends Entity {
  EntityType<EntityCamel> CAMEL_TYPE = EntityTypeBuilder
          .builder(EntityCamel.class)
          .vanillaEntity(VanillaEntityId.CAMEL)
          .build();
}
