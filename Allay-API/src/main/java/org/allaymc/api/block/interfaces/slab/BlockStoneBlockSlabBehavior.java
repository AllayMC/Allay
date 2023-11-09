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
public interface BlockStoneBlockSlabBehavior extends BlockBehavior {
  BlockType<BlockStoneBlockSlabBehavior> STONE_BLOCK_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockStoneBlockSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB)
          .setProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE, VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
          .build();
}
