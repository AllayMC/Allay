package org.allaymc.api.entity.interfaces.endermite;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityEndermite extends Entity {
  EntityType<EntityEndermite> ENDERMITE_TYPE = EntityTypeBuilder
          .builder(EntityEndermite.class)
          .vanillaEntity(VanillaEntityId.ENDERMITE)
          .build();
}
