package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCopperGrateBehavior extends BlockBehavior {
  BlockType<BlockCopperGrateBehavior> COPPER_GRATE_TYPE = BlockTypeBuilder
          .builder(BlockCopperGrateBehavior.class)
          .vanillaBlock(VanillaBlockId.COPPER_GRATE)
          .build();
}
