package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockPortalBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPortalBehaviorInitializer {
  static void init() {
    BlockTypes.PORTAL_TYPE = BlockTypeBuilder
            .builder(BlockPortalBehavior.class)
            .vanillaBlock(VanillaBlockId.PORTAL)
            .setProperties(VanillaBlockPropertyTypes.PORTAL_AXIS)
            .build();
  }
}
