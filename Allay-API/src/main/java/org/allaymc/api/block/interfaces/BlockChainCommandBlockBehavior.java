package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockChainCommandBlockBehavior extends BlockBehavior {
  BlockType<BlockChainCommandBlockBehavior> CHAIN_COMMAND_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockChainCommandBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.CHAIN_COMMAND_BLOCK)
          .setProperties(VanillaBlockPropertyTypes.CONDITIONAL_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .build();
}
