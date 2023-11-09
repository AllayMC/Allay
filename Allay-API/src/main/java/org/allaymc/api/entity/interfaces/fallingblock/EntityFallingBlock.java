package org.allaymc.api.entity.interfaces.fallingblock;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityFallingBlock extends Entity {
  EntityType<EntityFallingBlock> FALLING_BLOCK_TYPE = EntityTypeBuilder
          .builder(EntityFallingBlock.class)
          .vanillaEntity(VanillaEntityId.FALLING_BLOCK)
          .build();
}
