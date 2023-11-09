package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockInfoUpdate2Behavior extends BlockBehavior {
  BlockType<BlockInfoUpdate2Behavior> INFO_UPDATE2_TYPE = BlockTypeBuilder
          .builder(BlockInfoUpdate2Behavior.class)
          .vanillaBlock(VanillaBlockId.INFO_UPDATE2)
          .build();
}
