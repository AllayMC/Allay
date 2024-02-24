package org.allaymc.server.block.initializer.slab;

import org.allaymc.api.block.interfaces.slab.BlockStoneBlockSlab4Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockStoneBlockSlab4BehaviorInitializer {
  static void init() {
    BlockTypes.STONE_BLOCK_SLAB4_TYPE = BlockTypeBuilder
            .builder(BlockStoneBlockSlab4Behavior.class)
            .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB4)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF, VanillaBlockPropertyTypes.STONE_SLAB_TYPE_4)
            .build();
  }
}
