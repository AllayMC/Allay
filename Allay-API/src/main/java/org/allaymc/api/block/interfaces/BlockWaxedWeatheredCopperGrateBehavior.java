package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWaxedWeatheredCopperGrateBehavior extends BlockBehavior {
  BlockType<BlockWaxedWeatheredCopperGrateBehavior> WAXED_WEATHERED_COPPER_GRATE_TYPE = BlockTypeBuilder
          .builder(BlockWaxedWeatheredCopperGrateBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_COPPER_GRATE)
          .build();
}
