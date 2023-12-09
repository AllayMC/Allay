package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWaxedExposedCopperGrateBehavior extends BlockBehavior {
  BlockType<BlockWaxedExposedCopperGrateBehavior> WAXED_EXPOSED_COPPER_GRATE_TYPE = BlockTypeBuilder
          .builder(BlockWaxedExposedCopperGrateBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_COPPER_GRATE)
          .build();
}
