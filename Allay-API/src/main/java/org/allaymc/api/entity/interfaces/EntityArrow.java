package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityArrow extends Entity {
  EntityType<EntityArrow> ARROW_TYPE = EntityTypeBuilder
          .builder(EntityArrow.class)
          .vanillaEntity(VanillaEntityId.ARROW)
          .build();
}
