package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPowderSnowBehavior extends BlockBehavior {
  BlockType<BlockPowderSnowBehavior> POWDER_SNOW_TYPE = BlockTypeBuilder
          .builder(BlockPowderSnowBehavior.class)
          .vanillaBlock(VanillaBlockId.POWDER_SNOW)
          .build();
}
