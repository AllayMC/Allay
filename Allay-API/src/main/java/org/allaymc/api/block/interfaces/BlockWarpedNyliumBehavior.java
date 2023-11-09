package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWarpedNyliumBehavior extends BlockBehavior {
  BlockType<BlockWarpedNyliumBehavior> WARPED_NYLIUM_TYPE = BlockTypeBuilder
          .builder(BlockWarpedNyliumBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_NYLIUM)
          .build();
}
