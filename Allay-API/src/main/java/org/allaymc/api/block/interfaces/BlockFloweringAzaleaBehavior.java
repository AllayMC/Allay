package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockFloweringAzaleaBehavior extends BlockBehavior {
  BlockType<BlockFloweringAzaleaBehavior> FLOWERING_AZALEA_TYPE = BlockTypeBuilder
          .builder(BlockFloweringAzaleaBehavior.class)
          .vanillaBlock(VanillaBlockId.FLOWERING_AZALEA)
          .build();
}
