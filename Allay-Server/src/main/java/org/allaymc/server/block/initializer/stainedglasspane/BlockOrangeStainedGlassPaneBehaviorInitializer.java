package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockOrangeStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockOrangeStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.ORANGE_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockOrangeStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.ORANGE_STAINED_GLASS_PANE)
            .build();
  }
}
