package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

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
