package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardOrangeStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardOrangeStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_ORANGE_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardOrangeStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_ORANGE_STAINED_GLASS_PANE)
            .build();
  }
}
