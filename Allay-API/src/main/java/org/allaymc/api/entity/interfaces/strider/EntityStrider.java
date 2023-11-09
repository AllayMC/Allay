package org.allaymc.api.entity.interfaces.strider;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityStrider extends Entity {
  EntityType<EntityStrider> STRIDER_TYPE = EntityTypeBuilder
          .builder(EntityStrider.class)
          .vanillaEntity(VanillaEntityId.STRIDER)
          .build();
}
