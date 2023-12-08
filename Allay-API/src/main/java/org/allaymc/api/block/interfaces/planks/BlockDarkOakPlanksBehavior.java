package org.allaymc.api.block.interfaces.planks;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDarkOakPlanksBehavior extends BlockBehavior {
  BlockType<BlockDarkOakPlanksBehavior> DARK_OAK_PLANKS_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakPlanksBehavior.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_PLANKS)
          .build();
}
