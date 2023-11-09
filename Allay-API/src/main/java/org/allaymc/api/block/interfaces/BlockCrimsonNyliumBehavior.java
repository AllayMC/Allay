package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCrimsonNyliumBehavior extends BlockBehavior {
  BlockType<BlockCrimsonNyliumBehavior> CRIMSON_NYLIUM_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonNyliumBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_NYLIUM)
          .build();
}
