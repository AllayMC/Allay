package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockMossyCobblestoneBehavior extends BlockBehavior {
  BlockType<BlockMossyCobblestoneBehavior> MOSSY_COBBLESTONE_TYPE = BlockTypeBuilder
          .builder(BlockMossyCobblestoneBehavior.class)
          .vanillaBlock(VanillaBlockId.MOSSY_COBBLESTONE)
          .build();
}
