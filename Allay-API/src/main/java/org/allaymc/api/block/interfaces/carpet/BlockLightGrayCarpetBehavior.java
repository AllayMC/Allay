package org.allaymc.api.block.interfaces.carpet;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLightGrayCarpetBehavior extends BlockBehavior {
  BlockType<BlockLightGrayCarpetBehavior> LIGHT_GRAY_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockLightGrayCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_GRAY_CARPET)
          .build();
}
