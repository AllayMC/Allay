package org.allaymc.api.block.interfaces.shulkerbox;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLightGrayShulkerBoxBehavior extends BlockBehavior {
  BlockType<BlockLightGrayShulkerBoxBehavior> LIGHT_GRAY_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockLightGrayShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_GRAY_SHULKER_BOX)
          .build();
}
