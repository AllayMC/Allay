package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWaxedCopperGrateBehavior extends BlockBehavior {
  BlockType<BlockWaxedCopperGrateBehavior> WAXED_COPPER_GRATE_TYPE = BlockTypeBuilder
          .builder(BlockWaxedCopperGrateBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_COPPER_GRATE)
          .build();
}
