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
public interface BlockStoneBlockSlab2Behavior extends BlockBehavior {
  BlockType<BlockStoneBlockSlab2Behavior> STONE_BLOCK_SLAB2_TYPE = BlockTypeBuilder
          .builder(BlockStoneBlockSlab2Behavior.class)
          .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB2)
          .setProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE_2, VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
          .build();
}
