package org.allaymc.api.entity.interfaces.cat;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityCat extends Entity {
  EntityType<EntityCat> CAT_TYPE = EntityTypeBuilder
          .builder(EntityCat.class)
          .vanillaEntity(VanillaEntityId.CAT)
          .build();
}