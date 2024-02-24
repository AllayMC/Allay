package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCrackedDeepslateTilesBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCrackedDeepslateTilesBehaviorInitializer {
  static void init() {
    BlockTypes.CRACKED_DEEPSLATE_TILES_TYPE = BlockTypeBuilder
            .builder(BlockCrackedDeepslateTilesBehavior.class)
            .vanillaBlock(VanillaBlockId.CRACKED_DEEPSLATE_TILES)
            .build();
  }
}
