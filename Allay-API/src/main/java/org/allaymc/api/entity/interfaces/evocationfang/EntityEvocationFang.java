package org.allaymc.api.entity.interfaces.evocationfang;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityEvocationFang extends Entity {
  EntityType<EntityEvocationFang> EVOCATION_FANG_TYPE = EntityTypeBuilder
          .builder(EntityEvocationFang.class)
          .vanillaEntity(VanillaEntityId.EVOCATION_FANG)
          .build();
}
