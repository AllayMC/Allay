package org.allaymc.server.block.initializer.slab;

import org.allaymc.api.block.interfaces.slab.BlockTuffBrickSlabBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockTuffBrickSlabBehaviorInitializer {
  static void init() {
    BlockTypes.TUFF_BRICK_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockTuffBrickSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.TUFF_BRICK_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
  }
}
