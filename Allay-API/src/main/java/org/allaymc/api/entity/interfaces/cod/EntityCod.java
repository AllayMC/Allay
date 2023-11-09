package org.allaymc.api.entity.interfaces.cod;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityCod extends Entity {
  EntityType<EntityCod> COD_TYPE = EntityTypeBuilder
          .builder(EntityCod.class)
          .vanillaEntity(VanillaEntityId.COD)
          .build();
}
