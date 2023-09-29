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
public interface BlockChainBehavior extends BlockBehavior {
  BlockType<BlockChainBehavior> CHAIN_TYPE = BlockTypeBuilder
          .builder(BlockChainBehavior.class)
          .vanillaBlock(VanillaBlockId.CHAIN)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .build();
}
