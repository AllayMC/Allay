package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockHardOrangeStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardOrangeStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_ORANGE_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardOrangeStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_ORANGE_STAINED_GLASS)
            .build();
  }
}
