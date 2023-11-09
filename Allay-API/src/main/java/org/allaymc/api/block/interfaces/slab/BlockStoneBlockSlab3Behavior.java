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
public interface BlockStoneBlockSlab3Behavior extends BlockBehavior {
  BlockType<BlockStoneBlockSlab3Behavior> STONE_BLOCK_SLAB3_TYPE = BlockTypeBuilder
          .builder(BlockStoneBlockSlab3Behavior.class)
          .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB3)
          .setProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE_3, VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
          .build();
}
