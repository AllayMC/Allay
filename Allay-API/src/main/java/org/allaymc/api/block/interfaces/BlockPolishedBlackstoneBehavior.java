package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPolishedBlackstoneBehavior extends BlockBehavior {
  BlockType<BlockPolishedBlackstoneBehavior> POLISHED_BLACKSTONE_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE)
          .build();
}
