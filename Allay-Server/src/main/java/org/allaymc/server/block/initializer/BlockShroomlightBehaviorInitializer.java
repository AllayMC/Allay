package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockShroomlightBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockShroomlightBehaviorInitializer {
  static void init() {
    BlockTypes.SHROOMLIGHT_TYPE = BlockTypeBuilder
            .builder(BlockShroomlightBehavior.class)
            .vanillaBlock(VanillaBlockId.SHROOMLIGHT)
            .build();
  }
}
