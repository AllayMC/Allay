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
public interface BlockChainBehavior extends BlockBehavior {
  BlockType<BlockChainBehavior> CHAIN_TYPE = BlockTypeBuilder
          .builder(BlockChainBehavior.class)
          .vanillaBlock(VanillaBlockId.CHAIN)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .build();
}
