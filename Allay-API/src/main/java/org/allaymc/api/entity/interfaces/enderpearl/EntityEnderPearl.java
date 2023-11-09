package org.allaymc.api.entity.interfaces.enderpearl;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityEnderPearl extends Entity {
  EntityType<EntityEnderPearl> ENDER_PEARL_TYPE = EntityTypeBuilder
          .builder(EntityEnderPearl.class)
          .vanillaEntity(VanillaEntityId.ENDER_PEARL)
          .build();
}
