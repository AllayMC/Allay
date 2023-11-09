package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDriedKelpBlockBehavior extends BlockBehavior {
  BlockType<BlockDriedKelpBlockBehavior> DRIED_KELP_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockDriedKelpBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.DRIED_KELP_BLOCK)
          .build();
}
