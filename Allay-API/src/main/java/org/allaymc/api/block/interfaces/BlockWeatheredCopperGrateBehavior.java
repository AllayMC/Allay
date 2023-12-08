package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWeatheredCopperGrateBehavior extends BlockBehavior {
  BlockType<BlockWeatheredCopperGrateBehavior> WEATHERED_COPPER_GRATE_TYPE = BlockTypeBuilder
          .builder(BlockWeatheredCopperGrateBehavior.class)
          .vanillaBlock(VanillaBlockId.WEATHERED_COPPER_GRATE)
          .build();
}
