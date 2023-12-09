package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWaxedOxidizedCopperGrateBehavior extends BlockBehavior {
  BlockType<BlockWaxedOxidizedCopperGrateBehavior> WAXED_OXIDIZED_COPPER_GRATE_TYPE = BlockTypeBuilder
          .builder(BlockWaxedOxidizedCopperGrateBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_COPPER_GRATE)
          .build();
}
