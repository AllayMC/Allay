package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockInfoUpdateBehavior extends BlockBehavior {
  BlockType<BlockInfoUpdateBehavior> INFO_UPDATE_TYPE = BlockTypeBuilder
          .builder(BlockInfoUpdateBehavior.class)
          .vanillaBlock(VanillaBlockId.INFO_UPDATE)
          .build();
}
