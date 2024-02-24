package org.allaymc.server.block.initializer.slab;

import org.allaymc.api.block.interfaces.slab.BlockDoubleStoneBlockSlab3Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDoubleStoneBlockSlab3BehaviorInitializer {
  static void init() {
    BlockTypes.DOUBLE_STONE_BLOCK_SLAB3_TYPE = BlockTypeBuilder
            .builder(BlockDoubleStoneBlockSlab3Behavior.class)
            .vanillaBlock(VanillaBlockId.DOUBLE_STONE_BLOCK_SLAB3)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF, VanillaBlockPropertyTypes.STONE_SLAB_TYPE_3)
            .build();
  }
}
