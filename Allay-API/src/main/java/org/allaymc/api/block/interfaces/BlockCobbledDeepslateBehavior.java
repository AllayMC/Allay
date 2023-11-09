package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCobbledDeepslateBehavior extends BlockBehavior {
  BlockType<BlockCobbledDeepslateBehavior> COBBLED_DEEPSLATE_TYPE = BlockTypeBuilder
          .builder(BlockCobbledDeepslateBehavior.class)
          .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE)
          .build();
}
