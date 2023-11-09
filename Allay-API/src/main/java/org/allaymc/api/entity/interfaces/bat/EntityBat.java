package org.allaymc.api.entity.interfaces.bat;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityBat extends Entity {
  EntityType<EntityBat> BAT_TYPE = EntityTypeBuilder
          .builder(EntityBat.class)
          .vanillaEntity(VanillaEntityId.BAT)
          .build();
}
