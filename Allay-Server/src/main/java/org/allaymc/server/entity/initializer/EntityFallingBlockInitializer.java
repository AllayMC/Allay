package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityFallingBlock;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityFallingBlockInitializer {
  static void init() {
    EntityTypes.FALLING_BLOCK_TYPE = EntityTypeBuilder
            .builder(EntityFallingBlock.class)
            .vanillaEntity(VanillaEntityId.FALLING_BLOCK)
            .build();
  }
}
