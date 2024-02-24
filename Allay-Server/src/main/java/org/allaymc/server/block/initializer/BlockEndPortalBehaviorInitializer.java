package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockEndPortalBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockEndPortalBehaviorInitializer {
  static void init() {
    BlockTypes.END_PORTAL_TYPE = BlockTypeBuilder
            .builder(BlockEndPortalBehavior.class)
            .vanillaBlock(VanillaBlockId.END_PORTAL)
            .build();
  }
}
