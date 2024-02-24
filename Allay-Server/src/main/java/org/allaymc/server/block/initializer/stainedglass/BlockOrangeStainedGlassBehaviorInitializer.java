package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockOrangeStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockOrangeStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.ORANGE_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockOrangeStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.ORANGE_STAINED_GLASS)
            .build();
  }
}
