package org.allaymc.server.block.initializer.slab;

import org.allaymc.api.block.interfaces.slab.BlockDeepslateTileSlabBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDeepslateTileSlabBehaviorInitializer {
  static void init() {
    BlockTypes.DEEPSLATE_TILE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateTileSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_TILE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }
}
