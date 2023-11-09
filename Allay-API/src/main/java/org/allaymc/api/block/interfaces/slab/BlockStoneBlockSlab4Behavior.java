package org.allaymc.api.block.interfaces.slab;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockStoneBlockSlab4Behavior extends BlockBehavior {
  BlockType<BlockStoneBlockSlab4Behavior> STONE_BLOCK_SLAB4_TYPE = BlockTypeBuilder
          .builder(BlockStoneBlockSlab4Behavior.class)
          .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB4)
          .setProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE_4, VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
          .build();
}
