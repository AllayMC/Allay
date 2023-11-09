package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockHardenedClayBehavior extends BlockBehavior {
  BlockType<BlockHardenedClayBehavior> HARDENED_CLAY_TYPE = BlockTypeBuilder
          .builder(BlockHardenedClayBehavior.class)
          .vanillaBlock(VanillaBlockId.HARDENED_CLAY)
          .build();
}
