package org.allaymc.api.block.interfaces.wool;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLightGrayWoolBehavior extends BlockBehavior {
  BlockType<BlockLightGrayWoolBehavior> LIGHT_GRAY_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockLightGrayWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_GRAY_WOOL)
          .build();
}
