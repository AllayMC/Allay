package org.allaymc.server.block.initializer.slab;

import org.allaymc.api.block.interfaces.slab.BlockDoubleStoneBlockSlabBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDoubleStoneBlockSlabBehaviorInitializer {
  static void init() {
    BlockTypes.DOUBLE_STONE_BLOCK_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockDoubleStoneBlockSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.DOUBLE_STONE_BLOCK_SLAB)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF, VanillaBlockPropertyTypes.STONE_SLAB_TYPE)
            .build();
  }
}
