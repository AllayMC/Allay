package org.allaymc.server.block.initializer.slab;

import org.allaymc.api.block.interfaces.slab.BlockDoubleStoneBlockSlab2Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDoubleStoneBlockSlab2BehaviorInitializer {
  static void init() {
    BlockTypes.DOUBLE_STONE_BLOCK_SLAB2_TYPE = BlockTypeBuilder
            .builder(BlockDoubleStoneBlockSlab2Behavior.class)
            .vanillaBlock(VanillaBlockId.DOUBLE_STONE_BLOCK_SLAB2)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF, VanillaBlockPropertyTypes.STONE_SLAB_TYPE_2)
            .build();
  }
}
