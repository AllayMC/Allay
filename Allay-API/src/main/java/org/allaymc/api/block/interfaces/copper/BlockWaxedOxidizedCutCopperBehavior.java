package org.allaymc.api.block.interfaces.copper;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWaxedOxidizedCutCopperBehavior extends BlockBehavior {
  BlockType<BlockWaxedOxidizedCutCopperBehavior> WAXED_OXIDIZED_CUT_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWaxedOxidizedCutCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_CUT_COPPER)
          .build();
}
