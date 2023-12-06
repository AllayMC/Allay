package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityLlamaSpit extends Entity {
  EntityType<EntityLlamaSpit> LLAMA_SPIT_TYPE = EntityTypeBuilder
          .builder(EntityLlamaSpit.class)
          .vanillaEntity(VanillaEntityId.LLAMA_SPIT)
          .build();
}
