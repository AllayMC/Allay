package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockChiseledPolishedBlackstoneBehavior extends BlockBehavior {
  BlockType<BlockChiseledPolishedBlackstoneBehavior> CHISELED_POLISHED_BLACKSTONE_TYPE = BlockTypeBuilder
          .builder(BlockChiseledPolishedBlackstoneBehavior.class)
          .vanillaBlock(VanillaBlockId.CHISELED_POLISHED_BLACKSTONE)
          .build();
}
