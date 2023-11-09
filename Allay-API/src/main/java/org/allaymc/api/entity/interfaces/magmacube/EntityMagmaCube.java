package org.allaymc.api.entity.interfaces.magmacube;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityMagmaCube extends Entity {
  EntityType<EntityMagmaCube> MAGMA_CUBE_TYPE = EntityTypeBuilder
          .builder(EntityMagmaCube.class)
          .vanillaEntity(VanillaEntityId.MAGMA_CUBE)
          .build();
}
