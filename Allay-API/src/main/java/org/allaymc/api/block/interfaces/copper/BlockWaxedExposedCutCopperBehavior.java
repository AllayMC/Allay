package org.allaymc.api.block.interfaces.copper;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWaxedExposedCutCopperBehavior extends BlockBehavior {
  BlockType<BlockWaxedExposedCutCopperBehavior> WAXED_EXPOSED_CUT_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWaxedExposedCutCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_CUT_COPPER)
          .build();
}
